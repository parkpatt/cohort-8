package learn.recordcollection.data;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
=======
import org.springframework.boot.test.context.SpringBootTest;
>>>>>>> 8126dd3ffffe2d21fd69b85f2a9954c24944cf59
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RecordJdbcTemplateRepositoryTest {

    @Autowired
    RecordJdbcTemplateRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

<<<<<<< HEAD
    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup){
            hasSetup = true;
            jdbcTemplate.update("call set_known_good_state");
=======
    static boolean hasSetUp = false;

    @BeforeEach
    void setup() {
        if (!hasSetUp) {
            hasSetUp = true;
            jdbcTemplate.update("call set_known_good_state();");
>>>>>>> 8126dd3ffffe2d21fd69b85f2a9954c24944cf59
        }
    }

    @Test
    void shouldFindAll() {
        List<Record> actual = repository.findAll();

        assertTrue(actual.size() >= 3);

        Record first = actual.get(0);

        assertEquals(1, first.getRecordId());
        assertEquals("Prince", first.getArtist());
        assertEquals("1999", first.getTitle());

        Record third = actual.get(2);

        assertEquals(3, third.getRecordId());
        assertEquals("Prince", third.getArtist());
        assertEquals("Purple Rain", third.getTitle());
    }

    @Test
    void shouldFindById() {
        Record actual = repository.findById(1);

        assertEquals(1, actual.getRecordId());
        assertEquals("Prince", actual.getArtist());
        assertEquals("1999", actual.getTitle());
    }

    @Test
    void shouldNotFindMissingById() {
        Record actual = repository.findById(99);

        assertNull(actual);
    }

    @Test
    void shouldAddRecord() {
        Record record = new Record();
        record.setArtist("Kermit the Frog");
        record.setTitle("Kermit Sings the Blues");
        record.setCondition(Condition.GOOD);
        record.setValue(2000.00);

        Record actual = repository.add(record);
        record.setRecordId(5);

        assertEquals(5, actual.getRecordId());
        assertEquals(record, actual);
    }

    @Test
    void shouldUpdate() {
        Record record = new Record();
        record.setRecordId(2);
        record.setArtist("Madhero");
        record.setTitle("Madheroism");
        record.setCondition(Condition.GOOD);
        record.setValue(1);

        assertTrue(repository.update(record));
    }

    @Test
    void shouldNotUpdateMissing() {
        Record record = new Record();
        record.setRecordId(106);
        record.setArtist("Cats");
        record.setTitle("Cats");
        record.setCondition(Condition.POOR);
        record.setValue(1000);

        assertFalse(repository.update(record));
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(4));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(106));
    }
}