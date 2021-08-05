package learn.recordcollection.models;

public class Record {
    private int recordId;
    private String artist;
    private String title;
    private Condition condition;
    private double value;

    public Record() { }

    public Record(int recordId, String artist, String title, Condition condition, double value) {
        this.recordId = recordId;
        this.artist = artist;
        this.title = title;
        this.condition = condition;
        this.value = value;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (recordId != record.recordId) return false;
        if (Double.compare(record.value, value) != 0) return false;
        if (artist != null ? !artist.equals(record.artist) : record.artist != null) return false;
        if (title != null ? !title.equals(record.title) : record.title != null) return false;
        return condition == record.condition;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = recordId;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
