package theater.data;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import theater.models.*;

public class ShowJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public ShowJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Show> showMapper = (rs, i) -> {
        Show show = new Show();
        show.setShowId(rs.getInt("show_id"));
        show.setTitle(rs.getString("title"));
        show.setGenreId(rs.getInt("genre_id"));

        Genre genre = new Genre();
        genre.setGenreId(rs.getInt("genre_id"));
        genre.setName(rs.getString("genre_name"));

        show.setGenre(genre);

        return show;
    };

    public Show findById(int id) {
        final String sql = "select s.show_id, s.title, s.genre_id, g.name as genre_name " +
                "from show s " +
                "join genre g on g.genre_id = s.genre_id " +
                "where s.show_id = ?;";
        return jdbcTemplate.queryForObject(sql, showMapper, id);
    }
}
