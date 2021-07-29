package corbos.data;

import corbos.models.Hero;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroRepositoryTest {

    static final String SEED_PATH = "./data/heroes-seed.csv";
    static final String TEST_PATH = "./data/heroes-test.csv";

    HeroRepository repository = new HeroRepository(TEST_PATH);

    @BeforeAll
    static void setUpOnce() throws IOException {
        Files.copy(Path.of(SEED_PATH),
                Path.of(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFind3() {
        var expected = List.of(
                new Hero(1, "Abe Sapien"),
                new Hero(321, "Hellboy"),
                new Hero(411, "Liz Sherman")
        );
        var actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindHellboy() {
        var expected = new Hero(321, "Hellboy");
        var actual = repository.findById(321);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindOneStartsWithLiz() {
        var expected = List.of(new Hero(411, "Liz Sherman"));
        var actual = repository.findNameStartsWith("Liz");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindEmptyStartsWithMooCow() {
        var expected = new ArrayList<Hero>();
        var actual = repository.findNameStartsWith("Moo Cow");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindAbsorbingMan() {
        var actual = repository.findById(5);
        assertNull(actual);
    }

    @Test
    void shouldAddScarletWitch() {
        var arg = new Hero(0, "Scarlet Witch");
        var expected = new Hero(412, "Scarlet Witch");

        var actual = repository.add(arg);
        assertEquals(expected, actual);

        actual = repository.findById(412);
        assertEquals(expected, actual);
    }
}