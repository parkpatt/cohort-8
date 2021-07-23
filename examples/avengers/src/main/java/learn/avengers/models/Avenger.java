package learn.avengers.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Avenger {

    private static BigDecimal DAYS_IN_YEAR = new BigDecimal("365");

    private int avengerId;
    private String name;
    private Alignment alignment;
    private LocalDate joinDate;
    private LocalDate endDate;
    private BigDecimal salary;
    private ArrayList<Vacation> vacations;

    public Avenger() { }

    public Avenger(int avengerId, String name, Alignment alignment, LocalDate joinDate, LocalDate endDate, BigDecimal salary) {
        this.avengerId = avengerId;
        this.name = name;
        this.alignment = alignment;
        this.joinDate = joinDate;
        this.endDate = endDate;
        this.salary = salary;
        this.vacations = new ArrayList<>();
    }

    public int getAvengerId() {
        return avengerId;
    }

    public void setAvengerId(int avengerId) {
        this.avengerId = avengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<Vacation> getVacations() {
        return new ArrayList<>(vacations);
    }

    public void addVacation(Vacation vacation) {
        vacations.add(vacation);
    }

    public BigDecimal calculateTotalSalary() {
        LocalDate start = LocalDate.parse(joinDate.toString());
        LocalDate end = endDate == null
                ? LocalDate.now()
                : LocalDate.parse(endDate.toString());
        return calculateSalary(start, end);
    }

    private BigDecimal calculateSalary(LocalDate start, LocalDate end) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal weekdayPay = getWeekdayPay();
        BigDecimal vacationPay = getVacationPay();

        while (start.compareTo(end) < 0) {
            total = isVacationDay(start)
                    ? total.add(vacationPay)
                    : total.add(weekdayPay);
            start = start.plusDays(1);
        }
        return total;
    }

    private BigDecimal getWeekdayPay() {
        return salary.divide(DAYS_IN_YEAR, 2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getVacationPay() {
        return getWeekdayPay()
                .multiply(new BigDecimal("1.50"))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean isVacationDay(LocalDate date) {
        boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        if (isWeekend) return true;

        for (Vacation vacation : vacations) {
            if (date.compareTo(vacation.getStartDate()) >= 0
                    && date.compareTo(vacation.getEndDate()) <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Avenger{" +
                "avengerId=" + avengerId +
                ", name='" + name + '\'' +
                ", alignment=" + alignment +
                ", joinDate=" + joinDate +
                ", endDate=" + endDate +
                ", salary=" + salary +
                '}';
    }
}
