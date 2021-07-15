package learn.recordcollection.data;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordFileRepository implements RecordRepository {

    private final String DELIMITER = ",";
    private final String DELIMITER_SUB = "~~~";
    private final String HEADER = "ID,Artist,Title,Condition,Value";

    private final String filePath;

    public RecordFileRepository(@Value("${recordsFilePath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Record> findAll() throws DataAccessException {
        ArrayList<Record> all = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                all.add(deserialize(line));
            }
        } catch (FileNotFoundException ex) {
            //
        } catch (IOException ex) {
            throw new DataAccessException("Can not access file: " + filePath, ex);
        }
        return all;
    }

    @Override
    public List<Record> findByArtist(String artist) throws DataAccessException {
        ArrayList<Record> result = new ArrayList<>();
        String test = artist.trim();
        for (Record record : findAll()) {
            if (record.getArtist().equalsIgnoreCase(test)) {
                result.add(record);
            }
        }
        return result;
    }

    @Override
    public Record add(Record record) throws DataAccessException {
        List<Record> all = findAll();
        record.setRecordId(getMaxId(all));
        all.add(record);
        writeToFile(all);
        return record;
    }

    @Override
    public boolean update(Record record) throws DataAccessException {
        List<Record> all = findAll();
        for(int i = 0; i < all.size(); i++) {
            if (all.get(i).getRecordId() == record.getRecordId()) {
                all.set(i, record);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int recordId) throws DataAccessException {
        List<Record> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getRecordId() == recordId) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private int getMaxId(List<Record> all) {
        int maxId = 0;
        for(Record r : all) {
            if (r.getRecordId() > maxId) {
                maxId = r.getRecordId();
            }
        }
        return maxId + 1;
    }

    private void writeToFile(List<Record> records) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Record record : records) {
                String recordString = serialize(record);
                writer.println(recordString);
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not write to file: " + filePath, ex);
        }
    }

    private Record deserialize(String line) {
        String[] fields = line.split(DELIMITER);
        return new Record(
                Integer.parseInt(fields[0]),
                restoreString(fields[1]),
                restoreString(fields[2]),
                Condition.valueOf(fields[3]),
                Double.parseDouble(fields[4]));
    }

    private String serialize(Record record) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(record.getRecordId()).append(DELIMITER);
        stringBuilder.append(cleanField(record.getArtist())).append(DELIMITER);
        stringBuilder.append(cleanField(record.getTitle())).append(DELIMITER);
        stringBuilder.append(record.getCondition()).append(DELIMITER);
        stringBuilder.append(record.getValue());
        return stringBuilder.toString();
    }

    private String restoreString(String field) {
        return field.replace(DELIMITER_SUB, DELIMITER);
    }

    private String cleanField(String field) {
        return field.replace(DELIMITER, DELIMITER_SUB)
                .replace("\r", "")
                .replaceAll("\n", "");
    }
}
