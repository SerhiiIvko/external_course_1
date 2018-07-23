package task2;

import java.util.ArrayList;
import java.util.Collection;

public class CityRunner {
    public static void main(String[] args) {
        Collection<City> cityCollection = new ArrayList<>();
        cityCollection.add(new City("Aventura", "Florida", 37451));
        cityCollection.add(new City("Brandon", "Florida", 103483));
        cityCollection.add(new City("Brownsville", "Florida", 15313));
        cityCollection.add(new City("Casselberry", "Florida", 26707));
        cityCollection.add(new City("Cocoa Beach", "Florida", 11400));
        cityCollection.add(new City("Coral Springs", "Florida", 127952));
        cityCollection.add(new City("Hollywood", "Florida", 148047));
        cityCollection.add(new City("Miami", "Florida", 430332));
        cityCollection.add(new City("Weston", "Florida", 69100));
        CityManager cityManager = new CityManager(cityCollection);
        System.out.println(cityManager.getLargestCityPerState(cityCollection).values());
    }
}