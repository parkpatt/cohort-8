package learn.recordcollection.data;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordFileRepositoryTest {

    private final String SEED_FILE_PATH = "./data/records-seed.csv";
    private final String TEST_FILE_PATH = "./data/records-test.csv";

    private RecordFileRepository repository = new RecordFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Record> actual = repository.findAll();

        assertNotNull(actual);
        assertEquals(4, actual.size());

        Record first = actual.get(0);
        assertEquals(1, first.getRecordId());
        assertEquals("Prince", first.getArtist());
    }

    @Test
    void shouldFindByArtist() throws DataAccessException {
        // 1,Prince,Sign `O` the Times,NEAR_MINT
        // 4,Prince,1999,VERY_GOOD
        String artist = "Prince";

        List<Record> actual = repository.findByArtist(artist);

        assertEquals(2, actual.size());

        Record signOTheTimes = actual.get(0);
        Record nines = actual.get(1);

        assertEquals(1, signOTheTimes.getRecordId());
        assertEquals("Sign `O` the Times", signOTheTimes.getTitle());
        assertEquals(4, nines.getRecordId());
        assertEquals("1999", nines.getTitle());
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Record record = new Record();
        record.setArtist("Kendrick Lamar");
        record.setTitle("Good Kid M.A.A.D. City");
        record.setCondition(Condition.GOOD);

        Record actual = repository.add(record);

        assertNotNull(actual);
        assertEquals(5, actual.getRecordId());

        List<Record> all = repository.findAll();

        assertEquals(5, all.size());

        Record last = all.get(4);

        assertEquals("Kendrick Lamar", last.getArtist());
        assertEquals("Good Kid M.A.A.D. City", last.getTitle());
        assertEquals(5, last.getRecordId());
        assertEquals(Condition.GOOD, last.getCondition());
    }

    @Test
    void shouldHandleDelimiterInFields() throws DataAccessException {
        Record record = new Record();
        record.setArtist("Bon Iver");
        record.setTitle("For Emma, Forever Ago");
        record.setCondition(Condition.GOOD);

        Record actual = repository.add(record);

        assertNotNull(actual);
        assertEquals(5, actual.getRecordId());

        List<Record> all = repository.findAll();

        assertEquals(5, all.size());

        Record last = all.get(4);

        assertEquals("Bon Iver", last.getArtist());
        assertEquals("For Emma, Forever Ago", last.getTitle());
        assertEquals(5, last.getRecordId());
        assertEquals(Condition.GOOD, last.getCondition());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Record record = new Record(3, "Madvillain", "Madvillainy 2", Condition.POOR, 10);
        assertTrue(repository.update(record));
        List<Record> all = repository.findAll();
        assertEquals("Madvillainy 2", all.get(2).getTitle());
    }

    @Test
    void shouldNotUpdateNotExisting() throws DataAccessException {
        Record record = new Record(999, "Madvillain", "Madvillainy 2", Condition.POOR, 10);
        assertFalse(repository.update(record));
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        assertTrue(repository.deleteById(2));
        List<Record> all = repository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    void shouldNotDeleteNotExisting() throws DataAccessException {
        assertFalse(repository.deleteById(999));
        List<Record> all = repository.findAll();
        assertEquals(4, all.size());
    }
}