package learn.recordcollection.data;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordFileRepository {

    private final String DELIMITER = ",";
    private final String HEADER = "ID,Artist,Title,Condition";

    private final String filePath;

    public RecordFileRepository(String filePath) {
        this.filePath = filePath;
    }

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

    public Record add(Record record) throws DataAccessException {
        List<Record> all = findAll();
        record.setRecordId(getMaxId(all));
        all.add(record);
        writeToFile(all);
        return record;
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
        Record record = new Record();
        record.setRecordId(Integer.parseInt(fields[0]));
        record.setArtist(fields[1]);
        record.setTitle(fields[2]);
        record.setCondition(Condition.valueOf(fields[3]));
        return record;
    }

    private String serialize(Record record) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(record.getRecordId()).append(DELIMITER);
        stringBuilder.append(record.getArtist()).append(DELIMITER);
        stringBuilder.append(record.getTitle()).append(DELIMITER);
        stringBuilder.append(record.getCondition());
        return stringBuilder.toString();
    }
}
