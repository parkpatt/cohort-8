package learn.recordcollection.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DbConfig {

    private final String connectionString;
    private final String userName;
    private final String pwd;

    public DbConfig(
            @Value("${dbConnection}") String connectionString,
            @Value("${dbUserName}") String userName,
            @Value("${dbPassword}") String pwd) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.pwd = pwd;
    }

    @Bean
    public DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(connectionString);
        dataSource.setUser(userName);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
