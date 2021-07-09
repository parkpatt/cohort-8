package learn.recordcollection.domain;

import learn.recordcollection.data.DataAccessException;
import learn.recordcollection.data.RecordFileRepository;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.data.RecordRepositoryDouble;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordServiceTest {

    RecordRepository repository = new RecordRepositoryDouble();
    RecordService service = new RecordService(repository);

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Record> actual = service.findAll();

        assertEquals(4, actual.size());
        assertEquals(1, actual.get(0).getRecordId());
    }

    @Test
    void shouldFindByArtist() throws DataAccessException {
        List<Record> actual = service.findByArtist("Prince");

        assertEquals(2, actual.size());
    }

    @Test
    void shouldAddRecord() throws DataAccessException {
        Record record = new Record(0, "Bon Iver", "For Emma, Forever Ago", Condition.FAIR, 20.50);

        RecordResult actual = service.add(record);

        assertTrue(actual.isSuccess());
        assertNotNull(actual.getRecord());
        assertEquals(0, actual.getMessages().size());
    }

    @Test
    void shouldNotAddNullRecord() throws DataAccessException {
        RecordResult actual = service.add(null);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Record is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithEmptyArtist() throws DataAccessException {
        Record record = new Record(0, "", "For Emma, Forever Ago", Condition.FAIR, 20.50);

        RecordResult actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Artist is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithNullArtist() throws DataAccessException {
        Record record = new Record(0, null, "For Emma, Forever Ago", Condition.FAIR, 20.50);

        RecordResult actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Artist is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithEmptyTitle() throws DataAccessException {
        Record record = new Record(0, "Bon Iver", "", Condition.FAIR, 20.50);

        RecordResult actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Title is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithNullTitle() throws DataAccessException {
        Record record = new Record(0, "Bon Iver", null, Condition.FAIR, 20.50);

        RecordResult actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Title is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithNegativeValue() throws DataAccessException {
        Record record = new Record(0, "Bon Iver", "For Emma, Forever Ago", Condition.FAIR, -1.0);

        RecordResult actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Value should be between $0-10,000,000.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithValueOverTenMillion() throws DataAccessException {
        Record record = new Record(0, "Bon Iver", "For Emma, Forever Ago", Condition.FAIR, 10000000.01);

        RecordResult actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Value should be between $0-10,000,000.", actual.getMessages().get(0));
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Record record = new Record(11, "Test Artist", "Test Title", Condition.FAIR, 99);

        RecordResult actual = service.update(record);

        assertTrue(actual.isSuccess());
        assertEquals(record, actual.getRecord());
        assertEquals(0, actual.getMessages().size());
    }

    @Test
    void shouldNotUpdateNotExisting() throws DataAccessException {
        Record record = new Record(12, "Test Artist", "Test Title", Condition.FAIR, 99);

        RecordResult actual = service.update(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().get(0).contains("was not found."));
    }

    @Test
    void shouldNotUpdateWithoutId() throws DataAccessException {
        Record record = new Record(0, "Test Artist", "Test Title", Condition.FAIR, 99);

        RecordResult actual = service.update(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().get(0).contains("Record id is required."));
    }

    @Test
    void shouldDelete() throws DataAccessException {
        RecordResult actual = service.deleteById(11);

        assertTrue(actual.isSuccess());
        assertEquals(0, actual.getMessages().size());
    }

    @Test
    void shouldNotDeleteNotExisting() throws DataAccessException {
        RecordResult actual = service.deleteById(99);

        assertFalse(actual.isSuccess());
        assertNull(actual.getRecord());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().get(0).contains("was not found."));
    }
}