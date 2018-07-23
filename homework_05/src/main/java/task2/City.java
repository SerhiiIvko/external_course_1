package task2;

//Using Stream API implement a method that produces the largest city per state

public class City {
    private String cityName;
    private String state;
    private long population;

    public City(String cityName, String state, long population) {
        this.cityName = cityName;
        this.state = state;
        this.population = population;
    }

    public String getCityName() {
        return cityName;
    }

    public long getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "Largest city info: " + cityName +
                ", state: " + state +
                ", population: " + population;
    }
}