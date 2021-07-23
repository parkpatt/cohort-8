package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {
    final static int VALID_ID = 5;

    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return List.of(new Encounter(VALID_ID, EncounterType.CREATURE, "1/1/2015", "test description", 1));
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        return encounter;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return encounterId == VALID_ID;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        if (type == EncounterType.CREATURE) {
            return List.of(new Encounter(2, EncounterType.CREATURE, "1/1/2015", "test description", 1));
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return encounter.getEncounterId() == VALID_ID;
    }


}
