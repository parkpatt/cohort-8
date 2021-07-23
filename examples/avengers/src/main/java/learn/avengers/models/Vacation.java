package learn.avengers.models;

import java.time.LocalDate;

public class Vacation {
    private String vacationId;
    private int heroId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;

    public Vacation() { }

    public Vacation(String vacationId, int heroId, LocalDate startDate, LocalDate endDate, String location) {
        this.vacationId = vacationId;
        this.heroId = heroId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public String getVacationId() {
        return vacationId;
    }

    public void setVacationId(String vacationId) {
        this.vacationId = vacationId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
