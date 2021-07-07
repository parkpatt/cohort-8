package learn.recordcollection.data;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    void shouldAdd() throws DataAccessException {
        Record record = new Record();
        record.setArtist("Kendrick");
        record.setTitle("Good Kid M.A.A.D. City");
        record.setCondition(Condition.GOOD);

        Record actual = repository.add(record);

        assertNotNull(actual);
        assertEquals(5, actual.getRecordId());

        List<Record> all = repository.findAll();

        assertEquals(5, all.size());

        Record last = all.get(4);

        assertEquals("Kendrick", last.getArtist());
        assertEquals("Good Kid M.A.A.D. City", last.getTitle());
        assertEquals(5, last.getRecordId());
        assertEquals(Condition.GOOD, last.getCondition());
    }
}