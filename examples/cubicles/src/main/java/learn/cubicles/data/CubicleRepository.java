package learn.cubicles.data;

import learn.cubicles.models.Cubicle;

import java.util.List;

public interface CubicleRepository {
    List<Cubicle> findAll() throws DataAccessException;

    Cubicle create(Cubicle cubicle) throws DataAccessException;

    boolean update(Cubicle cubicle) throws DataAccessException;
}
