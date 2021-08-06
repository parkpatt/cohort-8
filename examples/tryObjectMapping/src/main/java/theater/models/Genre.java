package theater.models;

import java.util.*;

public class Genre {

    /*
     {
    "genreId": 1,
    "name": "Historical Musical"
    "shows": [
    ????
    ]
     }

    {
    "genreId": 1,
    "name": "Historical Musical",
    "shows": [
    {
      "showId": 1,
      "title": "Hamilton",
      "genreId": 1
    },
    {
      "showId": 2,
      "title": "Beetlejuice",
      "genreId": 1
    }
    ]
    }

     */

    private int genreId;
    private String name;

    private List<Show> shows;

    public Genre() {
    }

    public Genre(int genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
