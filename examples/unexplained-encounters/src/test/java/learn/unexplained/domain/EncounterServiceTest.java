package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepositoryDouble;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

    EncounterService service = new EncounterService(new EncounterRepositoryDouble());

    @Test
    void shouldNotAddNull() throws DataAccessException {
        EncounterResult expected = makeResult("encounter cannot be null");
        EncounterResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyWhen() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, " ", "test desc", 1);
        EncounterResult expected = makeResult("when is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "  ", 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", null, 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddZeroOccurrences() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 0);
        EncounterResult expected = makeResult("occurrences must be greater than 0");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "1/1/2015", "test description", 1);
        EncounterResult expected = makeResult("duplicate encounter is not allowed");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 1);
        EncounterResult expected = new EncounterResult();
        expected.setPayload(encounter);

        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    // updates
    @Test
    void shouldNotUpdateNull() throws DataAccessException {
        EncounterResult expected = makeResult("encounter cannot be null");
        EncounterResult actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyWhen() throws DataAccessException {
        Encounter encounter = new Encounter(5, EncounterType.CREATURE, " ", "test desc", 1);
        EncounterResult expected = makeResult("when is required");
        EncounterResult actual = service.update(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicate() throws DataAccessException {
        Encounter encounter = new Encounter(2, EncounterType.CREATURE, "1/1/2015", "test description", 1);
        EncounterResult expected = makeResult("duplicate encounter is not allowed");
        EncounterResult actual = service.update(encounter);
        assertEquals(expected, actual);
    }

    // delete tests aren't really that valuable
    @Test
    void shouldDeleteId5() throws DataAccessException {
        assertTrue(service.deleteById(5));
    }

    @Test
    void shouldNotDeleteId15() throws DataAccessException {
        assertFalse(service.deleteById(15));
    }

    private EncounterResult makeResult(String message) {
        EncounterResult result = new EncounterResult();
        result.addErrorMessage(message);
        return result;
    }
}