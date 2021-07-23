package learn.avengers.data;

import learn.avengers.models.Avenger;

import java.util.List;

public interface AvengerRepository {
    List<Avenger> findAll() throws DataAccessException;
    Avenger add(Avenger avenger) throws DataAccessException;
}
