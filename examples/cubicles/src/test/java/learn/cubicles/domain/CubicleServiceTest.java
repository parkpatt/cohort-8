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
}