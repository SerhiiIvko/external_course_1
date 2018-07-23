package task3;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTest<T> {
    private Stream<Integer> first;
    private Stream<Integer> second;

    @Test
    public void getZipStreamFromTwoStreams() {
        first = IntStream.iterate(1, x -> x + 2).limit(10).boxed();
        second = IntStream.iterate(2, x -> x + 2).limit(13).boxed();
        List<Integer> actual = StreamAlternates.zip(first, second).collect(toList());
        List<Integer> expected = IntStream.rangeClosed(1, 20).boxed().collect(toList());
        Assert.assertEquals(expected, actual);
    }
}