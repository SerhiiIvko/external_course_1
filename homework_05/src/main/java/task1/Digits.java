package task1;

import java.util.ArrayList;
import java.util.List;

//Using Stream API write a method that returns a comma separated string based on a given list of integers.
//Each element should be preceded by the letter 'e' if the number is even, and preceded by the letter 'o' if the
// number is odd.
//For example, if the input list is (3,44), the output should be 'o3,e44'.
public class Digits {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(44);
        integerList.add(22);
        integerList.add(111);
        integerList.add(999);
        integerList.add(66);

        DigitManager digits = new DigitManager();
        System.out.println(digits.getStringFromIntegers(integerList));
    }
}