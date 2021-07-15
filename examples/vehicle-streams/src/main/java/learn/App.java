package learn;

import learn.data.CarRepository;
import learn.models.Car;
import learn.models.Driver;
import learn.models.Make;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {

        CarRepository repository = new CarRepository();
        List<Car> cars = repository.findAll();

        // * Filtering
        // * Mapping/Transforming
        // * Aggregation
        // * Sorting


 //       HashMap<Integer, Integer> map = new HashMap<>();

//        Stream<Car> carStream = cars.stream();
//
//        cars.stream()
//                .filter(car -> car.getExteriorColor().equalsIgnoreCase("Orange"))
//                .map(car -> car.getVin())
//                .forEach(vin -> System.out.println(vin));

//        Set<Driver> driverStream = cars.stream()
//                .flatMap(car -> car.getDrivers().stream())
//                .collect(Collectors.toSet());


//        cars.stream()
//                .map(car -> car.getModel().getMake());

//        cars.stream()
//                .flatMap(car -> car.getDrivers().stream())
//                .flatMap(driver -> driver.getFaveColor().stream())
//                .forEach(color -> System.out.println(color));
//
//        cars.stream()
//                .flatMap(car -> car.getDrivers().stream())
//                .flatMap(driver -> Arrays.stream(driver.getFirstName().split("")))
//                .forEach(c -> System.out.println(c));


//        cars.stream()
//                .filter(car -> car.getExteriorColor().equalsIgnoreCase("Orange"))
//                .skip(10)
//                .limit(10)
//                .collect(Collectors.groupingBy(Car::getYear, Collectors.summarizingDouble(Car::getMiles)))
//                .entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEach(entry -> {
//                    System.out.println("Year: " + entry.getKey() + ", Miles: " + entry.getValue().getAverage());
//                });

//        for (int key : yearMilesMap.keySet().stream().sorted().collect(Collectors.toList())) {
//            System.out.println("Year: " + key + ", Miles: " + yearMilesMap.get(key).getAverage());
//        }

                //.forEach((k, v) -> System.out.println("Year: " + k + ", Miles: " + v.getAverage()));


        cars.stream()
                .filter(car -> car.getExteriorColor().equalsIgnoreCase("Orange")
                        && car.getYear() == 2010
                        && car.getDrivers().stream().anyMatch(d -> d.getFirstName().startsWith("D"))
                )
                .findFirst()
                .ifPresent(car -> System.out.println(car));

        Car theCar = cars.stream()
                .filter(car -> car.getExteriorColor().equalsIgnoreCase("Orange")
                        && car.getYear() == 2010
                        && car.getDrivers().stream().anyMatch(d -> d.getFirstName().startsWith("D"))
                )
                .findFirst()
                .orElse(null);


//        cars.stream()
//                .map(car -> new ColorModel(car.getYear(), car.getExteriorColor(), car.getModel().getName()))
//                .collect(Collectors.groupingBy(c -> c.getYear(), c -> c))



    }

    static class ColorModel {
        private int year;
        private String color;
        private String modelName;

        public ColorModel(int year, String color, String modelName) {
            this.year = year;
            this.color = color;
            this.modelName = modelName;
        }

        public int getYear() {
            return year;
        }

        public String getColor() {
            return color;
        }

        public String getModelName() {
            return modelName;
        }

        @Override
        public String toString() {
            return "ColorModel{" +
                    "year=" + year +
                    ", color='" + color + '\'' +
                    ", modelName='" + modelName + '\'' +
                    '}';
        }
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
                    "year=" + year +
                    ", make=" + make +
                    '}';
        }
    }
}
