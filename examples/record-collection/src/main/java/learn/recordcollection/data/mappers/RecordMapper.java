package learn.recordcollection.data.mappers;


import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet resultSet, int i) throws SQLException {
        Record record = new Record();
        record.setRecordId(resultSet.getInt("record_id"));
        record.setArtist(resultSet.getString("artist"));
        record.setTitle(resultSet.getString("title"));
        record.setValue(resultSet.getDouble("value"));
        return record;
    }
}
