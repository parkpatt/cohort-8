package learn;

import learn.data.CarRepository;
import learn.models.Car;
import learn.models.Driver;
import learn.models.Make;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {

        CarRepository repository = new CarRepository();
        List<Car> cars = repository.findAll();

        HashMap<Integer, Integer> map = new HashMap<>();

/*
        cars.stream()
                .map(car -> new YearMake(car.getYear(), car.getModel().getMake()));

        cars.stream()
                .filter(car -> car.getExteriorColor().equalsIgnoreCase("orange"))
                .map(Car::getVin)
                .forEach(System.out::println);

        Set<Driver> drivers = cars.stream()
                .flatMap(car -> car.getDrivers().stream())
                .collect(Collectors.toSet());
*/

        //Map<Integer, DoubleSummaryStatistics> yearMilesMap =
        /*cars.stream()
                .filter(car -> car.getExteriorColor().equalsIgnoreCase("orange"))
                .collect(Collectors.groupingBy(Car::getYear, Collectors.summarizingDouble(Car::getMiles)))
                .forEach((k, v) -> System.out.println("Year: " + k + ", Miles: " + v.getAverage()));*/

        System.out.println("Minnesota Drivers:");
        cars.stream()
                .flatMap(car -> car.getDrivers().stream())
                .filter(driver -> driver.getStateAbbr().equalsIgnoreCase("MN"))
                .forEach(driver -> System.out.println(driver.getFirstName() + " " + driver.getLastName()));

    }

    static class YearMake {
        private int year;
        private Make make;

        public YearMake(int year, Make make) {
            this.year = year;
            this.make = make;
        }

        @Override
        public String toString() {
            return "YearMake{" +
                    "year="
                    ;
        }
    }
}
