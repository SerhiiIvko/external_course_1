package com.epam;

import com.epam.processor.Consumer;
import com.epam.processor.Processor;
import com.epam.processor.Producer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class ProcessorTest {
    @Mock
    private Consumer consumer;
    @Mock
    private Producer producer;

    @InjectMocks
    private Processor processor = new Processor();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalStateException.class)
    public void whenProducerProduceNullThenThrowIllegalStateException(){
        //GIVEN:
        String expected = null;

        //WHEN:
        when(producer.produce()).thenReturn(expected);

        //THEN:
        processor.process();
        verify(producer, times(1)).produce();
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consumer).consume(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), equalTo(expected));
    }

    @Test
    public void whenProducerGetCorrectValue(){
        //GIVEN:
        String expected = "Magic value";

        //WHEN:
        when(producer.produce()).thenReturn(expected);
        processor.process();

        //THEN:
        verify(producer, times(1)).produce();
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consumer).consume(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), equalTo(expected));
    }
}