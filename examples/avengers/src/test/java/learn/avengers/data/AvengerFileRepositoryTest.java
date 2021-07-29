package learn.avengers.data;

import learn.avengers.models.Alignment;
import learn.avengers.models.Avenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvengerFileRepositoryTest {

    static final String SEED_PATH = "./data/avengers-seed.csv";
    static final String TEST_PATH = "./data/avengers-test.csv";

    private final AvengerFileRepository repository = new AvengerFileRepository(TEST_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Avenger> actual = repository.findAll();

        assertEquals(3, actual.size());
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Avenger avenger = new Avenger(
                0,
                "Captain Britain",
                Alignment.HERO,
                LocalDate.of(2020, 1, 1),
                null,
                new BigDecimal("100000.00"));

        Avenger actual = repository.add(avenger);

        assertEquals(4, actual.getAvengerId());

        List<Avenger> all = repository.findAll();
        assertEquals(4, all.size());
        assertEquals(4, all.get(3).getAvengerId());
    }
}