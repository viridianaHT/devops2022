package mx.tecnm.piedad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.piedad.models.Perfiles;

@Repository
public class PerfilesJDBC {
	 @Autowired
	    private JdbcTemplate conexion;

	    public int insertar(int cuenta_id, Perfiles perfil) {
	        String sql = "INSERT INTO perfiles_usuarios (nombre, idioma, clasificacion_edad, cuentas_id, activa) VALUES (?, ?, ?, ?, 1);";
	        conexion.update(sql,
	                perfil.getNombre(),
	                perfil.getIdioma(),
	                perfil.getClasificacion_edad(),
	                cuenta_id);
	        sql = "SELECT LAST_INSERT_ID()";
	        return conexion.queryForObject(sql, Integer.class);
	    }

	    public void modificar(int perfil_id, int cuenta_id, Perfiles perfil) {
	        String sql = "UPDATE perfiles_usuarios SET nombre = ?, idioma = ?,  clasificacion_edad = ? WHERE cuentas_id = ? and id = ?";
	        conexion.update(sql, perfil.getNombre(), perfil.getIdioma(), perfil.getClasificacion_edad(), cuenta_id,
	                perfil_id);
	    }

	    public List<Perfiles> listar(int cuenta_id) {
	        String sql = "SELECT * FROM perfiles_usuarios WHERE cuentas_id = ? and activa = 1";
	        return conexion.query(sql, new PerfilesRM(), cuenta_id);
	    }

	    public void desactivar(int cuenta_id, int perfil_id) {
	        String sql = "UPDATE perfiles_usuarios SET activa = 0 WHERE cuentas_id = ? and id = ?";
	        conexion.update(sql, cuenta_id, perfil_id);
	    }
}
