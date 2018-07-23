package task2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CityTest {
    private Collection<City> cities;
    private CityManager cityManager;
    private Map<String, City> cityMap;


    @Test
    public void getLargestCityFromListToMapWhenListIsEmpty() {
        cities = new ArrayList<>();
        cityManager = new CityManager(cities);

        cityMap = cityManager.getLargestCityPerState(cities);

        Assert.assertEquals(0, cityMap.size());
    }

    @Test
    public void getLargestCityFromListToMapWhenListContainsSomeElements() {
        String cityName1 = "London";
        String stateName1 = "UK";
        long population1 = 2000000;
        String cityName2 = "Tokyo";
        String stateName2 = "Japan";
        long population2 = 1000000;

        cities = new ArrayList<>();
        cityManager = new CityManager(cities);
        City city1 = new City(cityName1, stateName1, population1);
        cities.add(city1);
        City city2 = new City(cityName2, stateName2, population2);
        cities.add(city2);

        cityMap = cityManager.getLargestCityPerState(cities);
        String expectedKey = "[London]";
        String actualKey = cityMap.keySet().toString();
        Assert.assertEquals(1, cityMap.size());
        Assert.assertEquals(expectedKey, actualKey);
    }
}