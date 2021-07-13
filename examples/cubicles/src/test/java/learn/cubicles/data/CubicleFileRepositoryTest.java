package learn.cubicles.data;

import learn.cubicles.models.Cubicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CubicleFileRepositoryTest {

    final String SEED_PATH = "./data/cubicles-seed.txt";
    final String TEST_PATH = "./data/cubicles-test.txt";

    CubicleFileRepository repository = new CubicleFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findAll() throws DataAccessException {
        List<Cubicle> actual = repository.findAll();

        assertEquals(3, actual.size());

        Cubicle last = actual.get(2);
        assertEquals(3, last.getCubicleId());
        assertEquals(2, last.getFloor());
        assertEquals(1, last.getRow());
        assertEquals(1, last.getColumn());
    }

    @Test
    void shouldCreate() throws DataAccessException {
        Cubicle cubicle = new Cubicle(0, 5, 6, 7);

        Cubicle actual = repository.create(cubicle);

        assertNotNull(actual);
        assertEquals(4, actual.getCubicleId());

        List<Cubicle> all = repository.findAll();

        Cubicle last = all.get(3);

        assertEquals(5, last.getFloor());
        assertEquals(6, last.getRow());
        assertEquals(7, last.getColumn());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Cubicle cubicle = new Cubicle(1,2,3,4);

        assertTrue(repository.update(cubicle));

        List<Cubicle> all = repository.findAll();

        Cubicle first = all.get(0);

        assertEquals(2, first.getFloor());
        assertEquals(3, first.getRow());
        assertEquals(4, first.getColumn());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Cubicle cubicle = new Cubicle(99, 1, 1, 1);

        assertFalse(repository.update(cubicle));
    }
}