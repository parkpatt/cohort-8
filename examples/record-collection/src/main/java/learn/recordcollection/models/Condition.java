package learn.recordcollection.models;

public enum Condition {
    MINT("M"),
    NEAR_MINT("NM"),
    VERY_GOOD_PLUS("VG+"),
    VERY_GOOD("VG"),
    GOOD("G"),
    FAIR("F"),
    POOR("P");

    private final String abbreviation;

    Condition(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
