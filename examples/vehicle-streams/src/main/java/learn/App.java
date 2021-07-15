package learn;

import learn.data.CarRepository;
import learn.models.Car;

import java.util.List;

public class App {
    public static void main(String[] args) {

        CarRepository repository = new CarRepository();
        List<Car> cars = repository.findAll();


    }
}
