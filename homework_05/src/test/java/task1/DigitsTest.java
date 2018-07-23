package task1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DigitsTest {
    private List<Integer> integerList;
    private DigitManager digitManager;

    @Test
    public void getStringFromIntegersWhenIntegerIsOdd() {
        String expected = "o3";
        integerList = new ArrayList<>();
        integerList.add(3);
        String actual = "o" + integerList.get(0).toString();
        digitManager = new DigitManager();
        digitManager.getStringFromIntegers(integerList);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStringFromIntegersWhenIntegerIsEven() {
        String expected = "e66";
        integerList = new ArrayList<>();
        integerList.add(66);
        String actual = "e" + integerList.get(0).toString();
        digitManager = new DigitManager();
        digitManager.getStringFromIntegers(integerList);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }
}