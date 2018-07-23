package task1;

import java.util.List;
import java.util.stream.Collectors;

public class DigitManager {
    public String getStringFromIntegers(List<Integer> integers) {
        return integers.stream()
                .map(integer -> (integer % 2 == 0 ? "e" : "o") + integer)
                .collect(Collectors.joining(","));
    }
}