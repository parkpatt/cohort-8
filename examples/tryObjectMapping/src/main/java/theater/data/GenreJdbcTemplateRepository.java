package theater.data;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import theater.models.*;

import java.util.*;

public class GenreJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public GenreJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Genre> genreMapper = (rs, i) -> {
        Genre genre = new Genre();
        genre.setGenreId(rs.getInt("genre_id"));
        genre.setName(rs.getString("genre_name"));
        return genre;
    };

   private RowMapper<Show> showMapper = (rs, i) -> {
       Show show = new Show();
       show.setShowId(rs.getInt("show_id"));
       show.setTitle(rs.getString("title"));
       show.setGenreId(rs.getInt("genre_id"));
       return show;
   };

    public Genre findById(int id, boolean loadShows) {
        final String sql = "select genre_id, name from genre where genre_id = ?;";
        Genre genre = jdbcTemplate.queryForObject(sql, genreMapper, id);

        if (loadShows) {
            genre.setShows(getGenreShows(id));
        }

        return genre;
    }



    private List<Show> getGenreShows(int id) {
        final String sql = "select show_id, title, genre_id " +
                "from show " +
                "where genreId = ?;";
        return jdbcTemplate.query(sql, showMapper, id);
    }
}
