package interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomRowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
