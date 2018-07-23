package task3;

import java.util.Iterator;
import java.util.stream.Stream;

//Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
//that alternates elements from the streams first and second, stopping when
//one of them runs out of elements

public class StreamAlternates {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iteratorFirst = first.iterator();
        Iterator<T> iteratorSecond = second.iterator();
        Stream<T> resultStream = Stream.empty();
        while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
            resultStream = Stream.concat(resultStream, Stream.of(iteratorFirst.next(), iteratorSecond.next()));
        }
        return resultStream;
    }
}