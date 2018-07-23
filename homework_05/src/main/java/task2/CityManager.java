package task2;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CityManager {
    private Collection<City> cityCollection;

    public CityManager(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    public Map<String, City> getLargestCityPerState(Collection<City> cities) {
        return cities.stream()
                .collect(Collectors.toMap(City::getCityName, city -> city))
                .entrySet().stream()
                .sorted(Comparator.comparingLong(c -> -c.getValue().getPopulation()))
                .limit(1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (city, city2) -> city, LinkedHashMap::new));
    }
}