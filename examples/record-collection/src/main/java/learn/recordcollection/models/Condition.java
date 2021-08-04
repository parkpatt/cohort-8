package learn.recordcollection.models;

public enum Condition {
    MINT("M", 1),
    NEAR_MINT("NM", 2),
    VERY_GOOD_PLUS("VG+", 3),
    VERY_GOOD("VG", 4),
    GOOD("G", 5),
    FAIR("F", 6),
    POOR("P", 7);

    private final String abbreviation;
    final int id;

    Condition(String abbreviation, int id) {
        this.abbreviation = abbreviation;
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getId() {
        return id;
    }

    public static Condition getById(int conditionId) {
        if (conditionId < 1 || conditionId > 7) {
            throw new IllegalArgumentException("Condition Id must be 1-7.");
        }
        switch (conditionId) {
            case 1:
                return Condition.MINT;
            case 2:
                return Condition.NEAR_MINT;
            case 3:
                return Condition.VERY_GOOD_PLUS;
            case 4:
                return Condition.VERY_GOOD;
            case 5:
                return Condition.GOOD;
            case 6:
                return Condition.FAIR;
            default:
                return Condition.POOR;
        }
    }
}
