package mx.tecnm.piedad.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.piedad.models.Login;

@Repository
public class LoginJDBC {
	@Autowired
    JdbcTemplate conexion;

    public Login consultarUsuario(Login login) {
        String sql = "SELECT * FROM cuentas WHERE email = ? and password = ? and activa = 1";
        return conexion.queryForObject(sql, new LoginRM(), login.getEmail(), login.getPassword());
    }

}
