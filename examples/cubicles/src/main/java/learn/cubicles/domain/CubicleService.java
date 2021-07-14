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

    public CubicleResult create(Cubicle cubicle) throws DataAccessException {
        // row & col max = 20
        // row & col min = 1
        // floor max = 50
        // floor min = 1
        // no duplicate combo floor/row/col

        CubicleResult result = validate(cubicle);

        if (result.isSuccess()) {
            cubicle = repository.create(cubicle);
            result.setPayload(cubicle);
        }

        return result;
    }

    public CubicleResult update(Cubicle cubicle) throws DataAccessException {
        CubicleResult result = validate(cubicle);

        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(cubicle)) {
            result.addMessage( String.format("Cubicle not found, ID: %s", cubicle.getCubicleId()));
        }

        return result;
    }

    private CubicleResult validate(Cubicle cubicle) throws DataAccessException {
        CubicleResult result = new CubicleResult();

        if (cubicle == null) {
            result.addMessage("Cubicle should not be null.");
            return result;
        }

        if (cubicle.getFloor() > 50) {
            result.addMessage("Floor must be between 1-50.");
        }

        List<Cubicle> all = findAll();
        for (Cubicle c : all) {
            if (c.getFloor() == cubicle.getFloor()
                    && c.getRow() == cubicle.getRow()
                    && c.getColumn() == cubicle.getColumn()
                    && c.getCubicleId() != cubicle.getCubicleId()) {
                result.addMessage("Cannot save duplicate cubicle.");
                break;
            }
        }

        return result;
    }
}
