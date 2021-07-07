package learn.recordcollection.models;

public class Record {
    private int recordId;
    private String artist;
    private String title;
    private Condition condition;

    public Record() { }

    public Record(int recordId, String artist, String title, Condition condition) {
        this.recordId = recordId;
        this.artist = artist;
        this.title = title;
        this.condition = condition;
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
}
