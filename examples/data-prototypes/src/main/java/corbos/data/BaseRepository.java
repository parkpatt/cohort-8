package corbos.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseRepository<T> {

    private final String filePath;
    private final int fieldCount;
    private final String header;

    public BaseRepository(String filePath, int fieldCount, String header) {
        this.filePath = filePath;
        this.fieldCount = fieldCount;
        this.header = header;
    }

    public List<T> findAll() {
        ArrayList<T> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            if (header != null) {
                reader.readLine();
            }

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if (fields.length == fieldCount) {
                    result.add(deserialize(fields));
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    protected List<T> filter(Predicate<T> predicate) {
        return findAll().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    protected T filterOne(Predicate<T> predicate) {
        return findAll().stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    protected void write(List<T> values) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            if (header != null) {
                writer.println(header);
            }
            for (T value : values) {
                writer.println(serialize(value));
            }
        } catch (IOException ex) {
            throw new RuntimeException("Could not write to file path: " + filePath, ex);
        }
    }

    protected abstract String serialize(T value);

    protected abstract T deserialize(String[] fields);
}