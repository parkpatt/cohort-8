package theater.models;

public class Show {

    /*
    {
    "showId": 1,
    "title": "Hamilton",
    "genreId": 1
     }

     {
    "showId": 1,
    "title": "Hamilton",
    "genreId": 1,
    "genre": {
     "genreId": 1,
     "name": "Historical Musical"
     }

    select s.show_id, s.title, s.genre_id, g.name genre_name
    from show s
    inner join genre g on g.genre_id = s.genre_id
    where s.show_id = ?;

    Row Mapper

    Show show = new Show();
    // call show setters
    Genre genre = new Genre();
    // call genre setter
    Show.setGenre(genre);

     */

    private int showId;
    private String title;
    private int genreId;

    private Genre genre;

    public Show() {
    }

    public Show(int showId, String title, int genreId) {
        this.showId = showId;
        this.title = title;
        this.genreId = genreId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}