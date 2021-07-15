package learn.data;

import learn.models.Driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DriverRepository {

    public final Random random = new Random(101101);
    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> lastNames = new ArrayList<>();
    private String[] states = {"AK", "AL", "AZ", "AR", "CA", "CO", "CT", "DC",
            "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
            "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
            "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH",
            "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT",
            "VT", "VA", "WA", "WV", "WI", "WY"};

    public DriverRepository() {
        firstNames.addAll(readFile("./data/first-names.csv"));
        firstNames.addAll(readFile("./data/first-names-2.csv"));
        firstNames.addAll(readFile("./data/first-names-3.csv"));
        lastNames.addAll(readFile("./data/last-names.csv"));
        lastNames.addAll(readFile("./data/last-names-2.csv"));
    }

    public List<Driver> fetchDrivers(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Driver(
                        firstNames.get(random.nextInt(firstNames.size())),
                        lastNames.get(random.nextInt(lastNames.size())),
                        states[random.nextInt(states.length)]
                )).collect(Collectors.toList());
    }

    private List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
