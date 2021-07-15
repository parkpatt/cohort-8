package learn.models;

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
