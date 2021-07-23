package learn.avengers.models;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AvengerTest {

    @Test
    void shouldCalulateTotalSalaryForOneWeekday() {
        LocalDate joinDate = LocalDate.of(2021, 7,22);
        LocalDate endDate = LocalDate.of(2021, 7,23);

        Avenger tempAvenger = new Avenger(1, "Bob", Alignment.UNKNOWN, joinDate, endDate, new BigDecimal("365"));

        BigDecimal actual = tempAvenger.calculateTotalSalary();

        assertEquals(new BigDecimal("1.00"), actual);
    }

    @Test
    void shouldCalculateTotalSalaryForOneWeekendDay() {
        LocalDate joinDate = LocalDate.of(2021, 7,24);
        LocalDate endDate = LocalDate.of(2021, 7,25);

        Avenger tempAvenger = new Avenger(1, "Bob", Alignment.UNKNOWN, joinDate, endDate, new BigDecimal("365"));

        BigDecimal actual = tempAvenger.calculateTotalSalary();

        assertEquals(new BigDecimal("1.50"), actual);
    }

    @Test
    void shouldCalculateTotalSalaryForTwoWeekendDays() {
        LocalDate joinDate = LocalDate.of(2021, 7,24);
        LocalDate endDate = LocalDate.of(2021, 7,26);

        Avenger tempAvenger = new Avenger(1, "Bob", Alignment.UNKNOWN, joinDate, endDate, new BigDecimal("365"));

        BigDecimal actual = tempAvenger.calculateTotalSalary();

        assertEquals(new BigDecimal("3.00"), actual);
    }

    @Test
    void shouldCalulateTotalSalaryForOneWeek() {
        LocalDate joinDate = LocalDate.of(2021, 7,20);
        LocalDate endDate = LocalDate.of(2021, 7,27);

        Avenger tempAvenger = new Avenger(1, "Bob", Alignment.UNKNOWN, joinDate, endDate, new BigDecimal("365"));

        BigDecimal actual = tempAvenger.calculateTotalSalary();

        assertEquals(new BigDecimal("8.00"), actual);
    }


    @Test
    void shouldCalculateTotalSalaryForWeekVacation() {

        // 260 + 156 = 416

        LocalDate joinDate = LocalDate.of(2021, 1,1);
        LocalDate endDate = LocalDate.of(2021, 12,31);

        Avenger tempAvenger = new Avenger(1, "Bob", Alignment.UNKNOWN, joinDate, endDate, new BigDecimal("365"));

        tempAvenger.addVacation(new Vacation(null, 1,
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 7), "Iceland"));

        BigDecimal actual = tempAvenger.calculateTotalSalary();

        assertEquals(new BigDecimal("418.50"), actual);
    }
}