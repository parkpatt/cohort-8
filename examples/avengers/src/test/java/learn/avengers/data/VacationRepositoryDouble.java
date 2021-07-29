package learn.avengers.data;

import learn.avengers.models.Vacation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class VacationRepositoryDouble implements VacationRepository {

    @Override
    public List<Vacation> findByHeroId(int heroId) throws DataAccessException {
        return Arrays.asList(
                new Vacation("id1", 1, LocalDate.of(2017, 1, 1),
                        LocalDate.of(2017, 1, 15), "Spain"),
                new Vacation("id2", 1, LocalDate.of(2018, 1, 1),
                        LocalDate.of(2017, 1, 15), "Russia")
        );
    }

    @Override
    public Vacation add(Vacation vacation) throws DataAccessException {
        vacation.setVacationId("thisisanid");
        return vacation;
    }
}
