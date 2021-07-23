package learn.avengers.domain;

import learn.avengers.data.DataAccessException;
import learn.avengers.data.VacationRepositoryDouble;
import learn.avengers.models.Vacation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VacationServiceTest {

    VacationService service = new VacationService(new VacationRepositoryDouble());

    @Test
    void shouldFindByHeroId() throws DataAccessException {
        List<Vacation> actual = service.findByHeroId(1);
        assertEquals(2, actual.size());
    }

    @Test
    void shouldAddVacation() throws DataAccessException {
        Vacation vacation = new Vacation(null, 2,
                LocalDate.of(2021, 7, 21),
                LocalDate.of(2021, 7, 31),
                "Hawaii");

        Result<Vacation> actual = service.add(vacation);

        assertTrue(actual.isSuccess());
        assertNotNull(actual.getPayload());
        assertEquals("thisisanid", actual.getPayload().getVacationId());
    }

    @Test
    void shouldNotAddVacationWithoutLocation() throws DataAccessException {
        Vacation vacation = new Vacation(null, 2,
                LocalDate.of(2021, 7, 21),
                LocalDate.of(2021, 7, 31),
                null);

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());

        vacation = new Vacation(null, 2,
                LocalDate.of(2021, 7, 21),
                LocalDate.of(2021, 7, 31),
                "");

        actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Location is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddVacationWithoutHeroId() throws DataAccessException {
        Vacation vacation = new Vacation(null, 0,
                LocalDate.of(2021, 7, 21),
                LocalDate.of(2021, 7, 31),
                "Hawaii");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Hero id is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddVacationWithoutStartDate() throws DataAccessException {
        Vacation vacation = new Vacation(null, 2,
                null,
                LocalDate.of(2021, 7, 31),
                "Hawaii");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Start date is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddVacationWithoutEndDate() throws DataAccessException {
        Vacation vacation = new Vacation(null, 2,
                LocalDate.of(2021, 7, 31),
                null,
                "Hawaii");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("End date is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddVacationWithEndBeforeStart() throws DataAccessException {
        Vacation vacation = new Vacation(null, 2,
                LocalDate.of(2021, 8, 21),
                LocalDate.of(2021, 7, 31),
                "Hawaii");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Start date must be before end date.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddSurroundingDates() throws DataAccessException {
        // Completely surrounding existing vacation
        Vacation vacation = new Vacation(null, 1, LocalDate.of(2016, 12, 31),
                LocalDate.of(2017, 1, 16), "Tokyo");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Vacations cannot overlap.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddSurroundedByDates() throws DataAccessException {
        // Completely inside existing vacation
        Vacation vacation = new Vacation(null, 1, LocalDate.of(2017, 1, 5),
                LocalDate.of(2017, 1, 13), "Tokyo");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Vacations cannot overlap.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddOverlappingStart() throws DataAccessException {
        // Overlap start of existing vacation
        Vacation vacation = new Vacation(null, 1, LocalDate.of(2016, 12, 31),
                LocalDate.of(2017, 1, 13), "Tokyo");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Vacations cannot overlap.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotAddOverlappingEnd() throws DataAccessException {
        // Overlap end of existing vacation
        Vacation vacation = new Vacation(null, 1, LocalDate.of(2017, 1, 5),
                LocalDate.of(2017, 1, 17), "Tokyo");

        Result<Vacation> actual = service.add(vacation);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Vacations cannot overlap.", actual.getMessages().get(0));
    }
}