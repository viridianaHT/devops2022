package mx.tecnm.piedad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.piedad.models.Perfiles;

public class PerfilesRM implements RowMapper<Perfiles>{
	@Override
    public Perfiles mapRow(ResultSet rs, int rowNum) throws SQLException {
        Perfiles perfil = new Perfiles();
        perfil.setId(rs.getInt("id"));
        perfil.setNombre(rs.getString("nombre"));
        perfil.setIdioma(rs.getString("idioma"));
        perfil.setClasificacion_edad(rs.getString("clasificacion_edad"));
        perfil.setCuentas_id(rs.getInt("cuentas_id"));
        perfil.setActiva(rs.getInt("activa"));
        return perfil;

    }

}
