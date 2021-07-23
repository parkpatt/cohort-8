package learn.avengers.data;

import learn.avengers.models.Vacation;

import java.util.List;

public interface VacationRepository {

    List<Vacation> findByHeroId(int heroId) throws DataAccessException;

    Vacation add(Vacation vacation) throws DataAccessException;

}
