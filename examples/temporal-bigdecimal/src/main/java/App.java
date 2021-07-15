import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class App {

    public static void main(String[] args) {
        BigDecimal nines = new BigDecimal("9.34");

        BigDecimal[] change = nines.divideAndRemainder(BigDecimal.ONE);

        BigDecimal[] byThree = nines.divideAndRemainder(new BigDecimal("3"));

        BigDecimal[] byPointOne = nines.divideAndRemainder(new BigDecimal("0.1"));

        for (BigDecimal c : byPointOne) {
            System.out.println(c);
        }
    }

    public static void bigDecimalConstruction() {

        BigDecimal three = new BigDecimal(3);

        double doubleSeven = 7.99;

        BigDecimal seven = new BigDecimal(7.99);
        //seven = seven.setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal nines = new BigDecimal(9.99);
        BigDecimal nineNineNine = new BigDecimal("9.99");

        System.out.println(nines);
        System.out.println(nineNineNine);

        BigDecimal sevenNineNine = BigDecimal.valueOf(doubleSeven);

        BigDecimal n = null;



        //BigDecimal zero = BigDecimal.ZERO;



//        System.out.println(sevenNineNine);
//        System.out.println(seven);
//        System.out.println(nineNineNine);
    }

    public static void formatTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalTime nowMinusSeconds = LocalTime.of(localTime.getHour(), localTime.getMinute());

        System.out.println(nowMinusSeconds);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");

        System.out.println(localTime.format(formatter));
    }

    public static void daysBetween() {
        LocalDate date = LocalDate.now();
        LocalDate nextMonth = date.plusMonths(1);

        Period period = date.until(nextMonth);

        // just the "days" part, incomplete
        int someDays = period.getDays();

        // total days
        long days = ChronoUnit.DAYS.between(date, nextMonth);

        System.out.println(days);

        printPeriod(period);

        // watch out for null!
        printPeriod(null);
    }

    static void printPeriod(Period period) {
        System.out.printf("Days: %s, Months: %s, Years: %s%n",
                period.getDays(), period.getMonths(), period.getYears());
    }
}
