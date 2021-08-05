package learn.recordcollection.data;

import learn.recordcollection.models.Record;

import java.util.List;

public interface RecordRepository {
    List<Record> findAll();

    List<Record> findByArtist(String artist);

    Record findById(int recordId);

    Record add(Record record);

    boolean update(Record record);

    boolean deleteById(int recordId);
}
