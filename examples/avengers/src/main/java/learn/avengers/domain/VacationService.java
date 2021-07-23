package learn.avengers.domain;

import learn.avengers.data.DataAccessException;
import learn.avengers.data.VacationRepository;
import learn.avengers.models.Vacation;

import java.util.List;

public class VacationService {
    private final VacationRepository repository;

    public VacationService(VacationRepository repository) {
        this.repository = repository;
    }

    public List<Vacation> findByHeroId(int heroId) throws DataAccessException {
        return repository.findByHeroId(heroId);
    }

    public Result<Vacation> add(Vacation vacation) throws DataAccessException {
        Result<Vacation> result = validate(vacation);

        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(vacation));

        return result;
    }

    private Result<Vacation> validate(Vacation vacation) throws DataAccessException {
        Result<Vacation> result = new Result<>();

        if (vacation.getLocation() == null || vacation.getLocation().isBlank()) {
            result.addMessage("Location is required.");
        }

        if (vacation.getHeroId() <= 0) {
            result.addMessage("Hero id is required.");
        }

        if (vacation.getStartDate() == null) {
            result.addMessage("Start date is required.");
        }

        if (vacation.getEndDate() == null) {
            result.addMessage("End date is required.");
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (vacation.getStartDate().isAfter(vacation.getEndDate())) {
            result.addMessage("Start date must be before end date.");
        }

        if (isOverlapping(vacation)) {
            result.addMessage("Vacations cannot overlap.");
        }

        return result;
    }

    private boolean isOverlapping(Vacation vacation) throws DataAccessException {
        List<Vacation> existing = findByHeroId(vacation.getHeroId());
        for (Vacation v : existing) {
            if (!v.getVacationId().equals(vacation.getVacationId()) && datesOverlap(vacation, v)) {
                return true;
            }
        }
        return false;
    }

    private boolean datesOverlap(Vacation vacation, Vacation existing) {
        boolean totallyBefore = vacation.getEndDate().isBefore(existing.getStartDate())
                || vacation.getEndDate().equals(existing.getStartDate());
        boolean totallyAfter = vacation.getStartDate().isAfter(existing.getEndDate())
                || vacation.getStartDate().equals(existing.getEndDate());

        return !totallyBefore && !totallyAfter;
    }
}
