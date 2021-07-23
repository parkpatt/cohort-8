package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    static final String SEED_PATH = "./data/encounters-seed.csv";
    static final String TEST_PATH = "./data/encounters-test.csv";

    final Encounter[] testEncounters = new Encounter[]{
            new Encounter(1, EncounterType.UFO, "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND, "2020-03-01", "short test #3", 1)
    };

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH), StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> encounters = repository.findAll();
        Encounter[] actual = encounters.toArray(new Encounter[encounters.size()]);
        assertArrayEquals(testEncounters, actual);
    }

    @Test
    void shouldFind1Creature() throws DataAccessException {
        List<Encounter> encounters = repository.findByType(EncounterType.CREATURE);
        assertEquals(1, encounters.size());
    }

    @Test
    void shouldFind0Voice() throws DataAccessException {
        List<Encounter> encounters = repository.findByType(EncounterType.VOICE);
        assertEquals(0, encounters.size());
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldUpdateId2() throws DataAccessException {
        Encounter encounter = makeValidEncounter(2);
        assertTrue(repository.update(encounter));
    }

    @Test
    void shouldNotUpdateId12() throws DataAccessException {
        Encounter encounter = makeValidEncounter(12);
        assertFalse(repository.update(encounter));
    }

    @Test
    void shouldDeleteId1() throws DataAccessException {
        assertTrue(repository.deleteById(1));
    }

    @Test
    void shouldNotDeleteId21() throws DataAccessException {
        assertFalse(repository.deleteById(21));
    }

    private Encounter makeValidEncounter(int id) {
        return new Encounter(id, EncounterType.CREATURE, "2021-02-01", "test description", 1);
    }

}