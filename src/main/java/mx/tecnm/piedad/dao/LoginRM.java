package mx.tecnm.piedad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.piedad.models.Login;

public class LoginRM implements RowMapper<Login> {
	@Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login login = new Login();
        login.setEmail(rs.getString("email"));
        login.setPassword(rs.getString("password"));
        return login;
    }
}
