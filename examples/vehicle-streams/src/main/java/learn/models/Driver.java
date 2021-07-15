package learn.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {
    private final String firstName;
    private final String lastName;
    private final String stateAbbr;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public List<String> getFaveColor() {
        return Arrays.asList(
                "red",
                "yellow",
                "blue"
        );
    }

    public Driver(String firstName, String lastName, String stateAbbr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stateAbbr = stateAbbr;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stateAbbr='" + stateAbbr + '\'' +
                '}';
    }
}
