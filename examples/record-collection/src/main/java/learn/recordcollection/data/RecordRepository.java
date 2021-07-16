package learn.recordcollection.data;

import learn.recordcollection.models.Record;

import java.util.List;

public interface RecordRepository {
    List<Record> findAll() throws DataAccessException;

    List<Record> findByArtist(String artist) throws DataAccessException;

    Record add(Record record) throws DataAccessException;

    boolean update(Record record) throws DataAccessException;

    boolean deleteById(int recordId) throws DataAccessException;
}
