package learn.recordcollection.domain;

import learn.recordcollection.data.DataAccessException;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.models.Record;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    public static double MIN_VALUE = 0;
    public static double MAX_VALUE = 10000000;

    private final RecordRepository repository;

    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }

    public List<Record> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public List<Record> findByArtist(String artist) throws DataAccessException {
        return repository.findByArtist(artist);
    }

    public RecordResult add(Record record) throws DataAccessException {
        RecordResult result = validate(record);
        if (!result.isSuccess()) {
            return result;
        }
        if (record.getRecordId() > 0) {
            result.addMessage("Cannot add existing record.");
            return result;
        }
        result.setRecord(repository.add(record));
        return result;
    }

    public RecordResult update(Record record) throws DataAccessException {
        RecordResult result = validate(record);
        if (!result.isSuccess()) {
            return result;
        }
        if (record.getRecordId() <= 0) {
            result.addMessage("Record id is required.");
            return result;
        }
        if (repository.update(record)) {
            result.setRecord(record);
        } else {
            result.addMessage(String.format("Record id %s was not found.", record.getRecordId()));
        }
        return result;
    }

    public RecordResult deleteById(int recordId) throws DataAccessException {
        RecordResult result = new RecordResult();
        if (!repository.deleteById(recordId)) {
            result.addMessage(String.format("Record id %s was not found.", recordId));
        }
        return result;
    }

    private RecordResult validate(Record record) {
        RecordResult result = new RecordResult();
        if (record == null) {
            result.addMessage("Record is required.");
            return result;
        }
        if (record.getArtist() == null || record.getArtist().isBlank()) {
            result.addMessage("Artist is required.");
        }
        if (record.getTitle() == null || record.getTitle().isBlank()) {
            result.addMessage("Title is required.");
        }
        if (record.getValue() < MIN_VALUE || record.getValue() > MAX_VALUE) {
            result.addMessage("Value should be between $0-10,000,000.");
        }
        return result;
    }
}
