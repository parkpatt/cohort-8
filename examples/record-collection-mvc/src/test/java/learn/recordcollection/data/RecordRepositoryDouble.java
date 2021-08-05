package learn.recordcollection.data;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;

import java.util.Arrays;
import java.util.List;

public class RecordRepositoryDouble implements RecordRepository {

    @Override
    public List<Record> findAll() {
        return Arrays.asList(
                new Record(1, "Prince", "Sign `O` the Times", Condition.NEAR_MINT, 45),
                new Record(2, "Bob Dylan", "Blood on the Tracks", Condition.GOOD, 15),
                new Record(3, "Madvillain", "Madvillainy", Condition.NEAR_MINT, 30),
                new Record(4, "Prince", "1999", Condition.VERY_GOOD, 23)
        );
    }

    @Override
    public List<Record> findByArtist(String artist) {
        return Arrays.asList(
            new Record(1, "Prince", "Sign `O` the Times", Condition.NEAR_MINT, 45),
            new Record(4, "Prince", "1999", Condition.VERY_GOOD, 23)
        );
    }

    @Override
    public Record findById(int recordId) {
        return null;
    }

    @Override
    public Record add(Record record) {
        return record;
    }

    @Override
    public boolean update(Record record) {
        return record.getRecordId() == 11;
    }

    @Override
    public boolean deleteById(int recordId) {
        return recordId == 11;
    }
}
