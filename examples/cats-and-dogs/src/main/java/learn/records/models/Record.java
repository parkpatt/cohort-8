package learn.records.models;

import java.util.ArrayList;

public class Record {
    private String title;
    private String genre;
    private String artist;
    private String recordlabel;
    private ArrayList<String> songs;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordlabel() {
        return recordlabel;
    }

    public void setRecordlabel(String recordlabel) {
        this.recordlabel = recordlabel;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }
}
