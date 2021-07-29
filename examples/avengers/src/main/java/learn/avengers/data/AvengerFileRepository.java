package learn.avengers.data;

import learn.avengers.models.Alignment;
import learn.avengers.models.Avenger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AvengerFileRepository implements AvengerRepository {

    private final String HEADER = "ID,Name,Alignment,Gender,Join Date,End Date,Salary";
    private final String DELIMITER = ",";
    private final String DELIMITER_SUB = "~~~";

    private final String filePath;

    public AvengerFileRepository(@Value("${avengersDataPath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Avenger> findAll() throws DataAccessException {
        ArrayList<Avenger> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(DELIMITER);
                if (fields.length == 6) {
                    result.add(deserialize(fields));
                }
            }
        } catch (FileNotFoundException ex) {
            // File not present, OK
        } catch (IOException ex) {
            throw new DataAccessException("Could not access file: " + filePath, ex);
        }

        return result;
    }

    @Override
    public Avenger add(Avenger avenger) throws DataAccessException {
        List<Avenger> all = findAll();
        avenger.setAvengerId(nextId(all));
        all.add(avenger);
        writeToFile(all);
        return avenger;
    }

    private int nextId(List<Avenger> all) {
        return all.stream()
                .mapToInt(a -> a.getAvengerId())
                .max()
                .orElse(0) + 1;
    }

    private void writeToFile(List<Avenger> all) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Avenger avenger : all) {
                writer.println(serialize(avenger));
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not access file: " + filePath, ex);
        }
    }

    private String serialize(Avenger avenger) {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(avenger.getAvengerId()).append(DELIMITER);
        buffer.append(cleanField(avenger.getName())).append(DELIMITER);
        buffer.append(avenger.getAlignment()).append(DELIMITER);
        buffer.append(avenger.getJoinDate()).append(DELIMITER);
        buffer.append(avenger.getEndDate() == null ? "" : avenger.getEndDate()).append(DELIMITER);
        buffer.append(avenger.getSalary());
        return buffer.toString();
    }

    private Avenger deserialize(String[] values) {
        Avenger avenger = new Avenger();
        avenger.setAvengerId(Integer.parseInt(values[0]));
        avenger.setName(restoreField(values[1]));
        avenger.setAlignment(Alignment.valueOf(values[2].toUpperCase()));
        avenger.setJoinDate(LocalDate.parse(values[3]));
        avenger.setEndDate(values[4].isBlank() ? null : LocalDate.parse(values[4]));
        avenger.setSalary(new BigDecimal(values[5]));
        return avenger;
    }

    private String cleanField(String value) {
        return value.replace(DELIMITER, DELIMITER_SUB);
    }

    private String restoreField(String value) {
        return value.replace(DELIMITER_SUB, DELIMITER);
    }
}
