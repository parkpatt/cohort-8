package learn.cubicles.data;

import learn.cubicles.models.Cubicle;

import java.util.Arrays;
import java.util.List;

public class CubicleRepositoryDouble implements CubicleRepository {
    @Override
    public List<Cubicle> findAll() throws DataAccessException {
        return Arrays.asList(
                new Cubicle(1,1,1,1, "Midge"),
                new Cubicle(2,3,4,5, "Hermione"),
                new Cubicle(3,3,4,4, "Roger")
        );
    }

    @Override
    public Cubicle create(Cubicle cubicle) throws DataAccessException {
        return cubicle;
    }

    @Override
    public boolean update(Cubicle cubicle) throws DataAccessException {
        return cubicle.getCubicleId() == 1;
    }
}
