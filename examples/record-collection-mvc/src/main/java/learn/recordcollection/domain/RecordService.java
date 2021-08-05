package learn.recordcollection.domain;

import learn.recordcollection.data.DataAccessException;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.models.Record;
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

    public List<Record> findAll() {
        return repository.findAll();
    }

    public List<Record> findByArtist(String artist) {
        return repository.findByArtist(artist);
    }

    public Result<Record> add(Record record) {
        Result<Record> result = validate(record);
        if (!result.isSuccess()) {
            return result;
        }
        if (record.getRecordId() > 0) {
            result.addMessage("Cannot add existing record.");
            return result;
        }
        result.setPayload(repository.add(record));
        return result;
    }

    public Result<Record> update(Record record) {
        Result<Record> result = validate(record);
        if (!result.isSuccess()) {
            return result;
        }
        if (record.getRecordId() <= 0) {
            result.addMessage("Record id is required.");
            return result;
        }
        if (!repository.update(record)) {
            result.addMessage(String.format("Record id %s was not found.", record.getRecordId()));
        }
        return result;
    }

    public Result<Void> deleteById(int recordId) {
        Result<Void> result = new Result<>();
        if (!repository.deleteById(recordId)) {
            result.addMessage(String.format("Record id %s was not found.", recordId));
        }
        return result;
    }

    private Result<Record> validate(Record record) {
        Result<Record> result = new Result<>();
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
