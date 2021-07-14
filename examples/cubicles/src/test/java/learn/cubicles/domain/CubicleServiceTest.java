package learn.cubicles.domain;

import learn.cubicles.data.CubicleRepository;
import learn.cubicles.data.CubicleRepositoryDouble;
import learn.cubicles.data.DataAccessException;
import learn.cubicles.models.Cubicle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CubicleServiceTest {

    CubicleRepository repository = new CubicleRepositoryDouble();
    CubicleService service = new CubicleService(repository);

    @Test
    void findAll() throws DataAccessException {
        List<Cubicle> actual = service.findAll();
        assertEquals(3, actual.size());
    }

    @Test
    void shouldCreateCubicle() throws DataAccessException {
        Cubicle cubicle = new Cubicle(0, 6, 7, 8, "Al");

        CubicleResult actual = service.create(cubicle);

        assertTrue(actual.isSuccess());
        assertNotNull(actual.getPayload());
    }

    @Test
    void shouldNotCreateNullCubicle() throws DataAccessException {
        Cubicle cubicle = null;

        CubicleResult actual = service.create(cubicle);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertNull(actual.getPayload());
        assertEquals("Cubicle should not be null.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddCubicleOnFloorFiftyOne() throws DataAccessException {
        Cubicle cubicle = new Cubicle(0, 51, 1, 1, "Al");

        CubicleResult actual = service.create(cubicle);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertNull(actual.getPayload());
        assertEquals("Floor must be between 1-50.", actual.getMessages().get(0));
    }

    /// Omitted tests....

    @Test
    void shouldNotAddDuplicateFloorRowColumn() throws DataAccessException {
        Cubicle cubicle = new Cubicle(0, 1, 1, 1, "Al");

        CubicleResult actual = service.create(cubicle);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertNull(actual.getPayload());
        assertEquals("Cannot save duplicate cubicle.", actual.getMessages().get(0));
    }


    /// Omitted tests....

    @Test
    void shouldUpdateCubicle() throws DataAccessException {
        Cubicle cubicle = new Cubicle(1,1,1,1, "Al");

        CubicleResult actual = service.update(cubicle);

        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateDuplicateFloorRowColumn() throws DataAccessException {
        Cubicle cubicle = new Cubicle(3,1,1,1, "Al");

        CubicleResult actual = service.update(cubicle);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Cannot save duplicate cubicle.", actual.getMessages().get(0));
    }

}