package learn.recordcollection.data;

import learn.recordcollection.data.mappers.RecordMapper;
import learn.recordcollection.models.Record;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RecordJdbcTemplateRepository implements RecordRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RecordMapper mapper = new RecordMapper();

    private final String SELECT = "select " +
                "r.record_id, " +
                "r.artist, " +
                "r.title, " +
                "r.`value`, " +
                "c.`description` as `condition` " +
            "from record r " +
            "inner join `condition` c on c.condition_id = r.condition_id ";

    public RecordJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Record> findAll() throws DataAccessException {
        final String sql = String.format("%s;", SELECT);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Record> findByArtist(String artist) throws DataAccessException {
        final String sql = String.format("%s where r.artist = ?;", SELECT);
        return jdbcTemplate.query(sql, mapper, artist);
    }

    @Override
    public Record findById(int recordId) throws DataAccessException {
        final String sql = String.format("%s where r.record_id = ?;", SELECT);
        return jdbcTemplate.query(sql, mapper, recordId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Record add(Record record) throws DataAccessException {
        final String sql = "insert into record (" +
                " artist," +
                " title," +
                " condition_id," +
                " `value`" +
                " ) values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, record.getArtist());
            ps.setString(2, record.getTitle());
            ps.setInt(3, record.getCondition().getId());
            ps.setDouble(4, record.getValue());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        record.setRecordId(keyHolder.getKey().intValue());
        return record;
    }

    @Override
    public boolean update(Record record) throws DataAccessException {
        final String sql = "update record set " +
                "artist = ?, " +
                "title = ?, " +
                "condition_id = ?, " +
                "`value` = ? " +
                "where record_id = ?";

        int rowsUpdated = jdbcTemplate.update(sql,
                record.getArtist(),
                record.getTitle(),
                record.getCondition().getId(),
                record.getValue(),
                record.getRecordId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int recordId) throws DataAccessException {
        final String sql = "delete from record where record_id = ?;";
        return jdbcTemplate.update(sql, recordId) > 0;
    }
}
