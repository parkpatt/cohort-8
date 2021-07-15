package learn.data;

import learn.models.Car;
import learn.models.Driver;
import learn.models.Make;
import learn.models.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarRepository {

    private HashMap<String, Make> makes = new HashMap<>();
    private HashMap<String, Model> models = new HashMap<>();
    private DriverRepository driverRepository = new DriverRepository();

    public List<Car> findAll() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.addAll(readFile("./data/cars.csv"));
        cars.addAll(readFile("./data/cars-2.csv"));
        return cars;
    }


    private List<Car> readFile(String path) {
        ArrayList<Car> cars = new ArrayList<>();
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)) {
            reader.readLine(); // toss out header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Car car = makeCar(line.split(",", -1));
                if (car != null) {
                    cars.add(car);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    private Car makeCar(String[] fields) {
        if (fields.length != 7) {
            return null;
        }
        // vin,make,model,year,exterior,interior,miles
        Car car = new Car();
        car.setVin(fields[0]);
        setMakeModel(car, fields[1], fields[2]);
        car.setYear(Integer.parseInt(fields[3]));
        car.setExteriorColor(fields[4]);
        car.setInteriorColor(fields[5]);
        car.setMiles(Integer.parseInt(fields[6]));
        setDrivers(car);
        return car;
    }

    private void setMakeModel(Car car, String makeName, String modelName) {
        if (!makes.containsKey(makeName)) {
            makes.put(makeName, new Make(makeName));
        }
        Make make = makes.get(makeName);

        // guarantee the combo is unique, not different models per make
        String combinedKey = makeName + "-" + modelName;
        if (!models.containsKey(combinedKey)) {
            models.put(combinedKey, new Model(modelName, make));
        }
        Model model = models.get(combinedKey);
        car.setModel(model);
    }

    private void setDrivers(Car car) {
        int driverCount = driverRepository.random.nextInt(4);
        List<Driver> drivers = driverRepository.fetchDrivers(driverCount);
        car.setDrivers(drivers);
    }
}
