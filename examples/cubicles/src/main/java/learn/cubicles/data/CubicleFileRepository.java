package learn.cubicles.data;

import learn.cubicles.models.Cubicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CubicleFileRepository {

    private final String DELIMITER = ",";
    private final String HEADER = "ID,FLOOR,ROW,COLUMN";

    private final String filePath;

    public CubicleFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Cubicle> findAll() throws DataAccessException {
        ArrayList<Cubicle> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                deserialize(line).ifPresent(c -> result.add(c));
            }
        } catch (FileNotFoundException ex) {
            // No file yet, OK
        } catch (IOException ex) {
            throw new DataAccessException("Failed to access file: " + filePath, ex);
        }
        return result;
    }

    public Cubicle create(Cubicle cubicle) throws DataAccessException {
        List<Cubicle> all = findAll();
        cubicle.setCubicleId(nextId(all));
        all.add(cubicle);
        writeToFile(all);
        return cubicle;
    }

    public boolean update(Cubicle cubicle) throws DataAccessException {
        List<Cubicle> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (cubicle.getCubicleId() == all.get(i).getCubicleId()) {
                all.set(i, cubicle);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private int nextId(List<Cubicle> cubicles) {
        return cubicles.stream()
                .map(Cubicle::getCubicleId)
                .reduce(Integer::max)
                .orElse(0) + 1;
    }

    private void writeToFile(List<Cubicle> cubicles) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(HEADER);
            for (Cubicle cubicle : cubicles) {
                writer.println(serialize(cubicle));
            }
        } catch (IOException e) {
            throw new DataAccessException("Failed to access file:" + filePath, e);
        }
    }

    private Optional<Cubicle> deserialize(String line) {
        String[] fields = line.split(DELIMITER);
        if (fields.length != 4) return Optional.empty();

        return Optional.of(new Cubicle(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[1]),
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3])
        ));
    }

    private String serialize(Cubicle cubicle) {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(cubicle.getCubicleId()).append(DELIMITER);
        buffer.append(cubicle.getFloor()).append(DELIMITER);
        buffer.append(cubicle.getRow()).append(DELIMITER);
        buffer.append(cubicle.getColumn());
        return buffer.toString();
    }
}
