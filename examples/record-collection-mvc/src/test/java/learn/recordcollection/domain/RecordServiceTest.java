package learn.recordcollection.domain;

import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.data.RecordRepositoryDouble;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RecordServiceTest {

    @MockBean
    RecordRepository repository;

    @Autowired
    RecordService service;

    @Test
    void shouldFindAll() {
        List<Record> actual = service.findAll();

        assertEquals(4, actual.size());
        assertEquals(1, actual.get(0).getRecordId());
    }

    @Test
    void shouldFindByArtist() {
        List<Record> actual = service.findByArtist("Prince");

        assertEquals(2, actual.size());
    }

    @Test
    void shouldAddRecord() {
        Record record = new Record(0, "Bon Iver", "For Emma, Forever Ago", Condition.FAIR, 20.50);

        when(repository.add(record)).thenReturn(record);

        Result<Record> actual = service.add(record);

        assertTrue(actual.isSuccess());
        assertNotNull(actual.getPayload());
        assertEquals(0, actual.getMessages().size());
    }

    @Test
    void shouldNotAddNullRecord() {
        Result<Record> actual = service.add(null);

        assertFalse(actual.isSuccess());
        assertEquals(ResultType.INVALID, actual.getResultType());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Record is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithEmptyArtist() {
        Record record = new Record(0, "", "For Emma, Forever Ago", Condition.FAIR, 20.50);

        Result<Record> actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Artist is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithNullArtist() {
        Record record = new Record(0, null, "For Emma, Forever Ago", Condition.FAIR, 20.50);

        Result<Record> actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Artist is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithEmptyTitle() {
        Record record = new Record(0, "Bon Iver", "", Condition.FAIR, 20.50);

        Result<Record> actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Title is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithNullTitle() {
        Record record = new Record(0, "Bon Iver", null, Condition.FAIR, 20.50);

        Result<Record> actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Title is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithNegativeValue() {
        Record record = new Record(0, "Bon Iver", "For Emma, Forever Ago", Condition.FAIR, -1.0);

        Result<Record> actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Value should be between $0-10,000,000.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddRecordWithValueOverTenMillion() {
        Record record = new Record(0, "Bon Iver", "For Emma, Forever Ago", Condition.FAIR, 10000000.01);

        Result<Record> actual = service.add(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Value should be between $0-10,000,000.", actual.getMessages().get(0));
    }

    @Test
    void shouldUpdate() {
        Record record = new Record(11, "Test Artist", "Test Title", Condition.FAIR, 99);

        Result<Record> actual = service.update(record);

        assertTrue(actual.isSuccess());
        assertEquals(0, actual.getMessages().size());
    }

    @Test
    void shouldNotUpdateNotExisting() {
        Record record = new Record(12, "Test Artist", "Test Title", Condition.FAIR, 99);

        Result<Record> actual = service.update(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().get(0).contains("was not found."));
    }

    @Test
    void shouldNotUpdateWithoutId() {
        Record record = new Record(0, "Test Artist", "Test Title", Condition.FAIR, 99);

        Result<Record> actual = service.update(record);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().get(0).contains("Record id is required."));
    }

    @Test
    void shouldDelete() {
        Result<Void> actual = service.deleteById(11);

        when(repository.deleteById(anyInt())).thenReturn(true);

        assertTrue(actual.isSuccess());
        assertEquals(0, actual.getMessages().size());
    }

    @Test
    void shouldNotDeleteNotExisting() {
        Result<Void> actual = service.deleteById(99);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().get(0).contains("was not found."));
    }
}