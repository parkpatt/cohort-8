package learn.cubicles.domain;

import learn.cubicles.data.CubicleRepository;
import learn.cubicles.data.DataAccessException;
import learn.cubicles.models.Cubicle;

import java.util.List;

public class CubicleService {

    private final CubicleRepository repository;

    public CubicleService(CubicleRepository repository) {
        this.repository = repository;
    }

    public List<Cubicle> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public CubicleResult create(Cubicle cubicle) {
        return null;
    }

    public CubicleResult updated(Cubicle cubicle) {
        return null;
    }
}
