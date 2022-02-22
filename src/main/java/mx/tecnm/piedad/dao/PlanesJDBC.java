package mx.tecnm.piedad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.piedad.models.Planes;

@Repository
public class PlanesJDBC {
	@Autowired
	JdbcTemplate conexion;
	
	public List<Planes> consultar() {
		String sql = "SELECT * FROM planes";
		return conexion.query(sql, new PlanesRM());
	}

	public Planes buscar(int id) {
		String sql = "SELECT * FROM planes WHERE id=?";
		return conexion.queryForObject(sql, new PlanesRM(), id);
	}
	
	/*public void desactivar(int id) {
		String sql = "UPDATE planes SET activo = 0, eliminado = NOW() WHERE id=?";
		conexion.update(sql, id);
	}*/
	
	public int insertar(Planes nuevo_plan) {

		String sql = "INSERT INTO planes(descripcion, precio_mensual,"
				+ "calidad_video, resolucion, cantidad_dispositivos) VALUES"
				+ "(?, ?, ?, ?, ?)";
		conexion.update(sql, nuevo_plan.getDescripcion(),
				nuevo_plan.getPrecio_mensual(),
				nuevo_plan.getCalidad_video(),
				nuevo_plan.getResolucion(),
				nuevo_plan.getCantidad_dispositivos());

		sql = "SELECT LAST_INSERT_ID()";
		return conexion.queryForObject(sql, Integer.class);
	}

	public void modificar(int id, Planes plan) {

		String sql = "UPDATE planes SET descripcion = ?, precio_mensual = ?,"
				+ "calidad_video = ?, resolucion = ?,"
				+ "cantidad_dispositivos = ?, modificado = NOW() WHERE id = ?";
		conexion.update(sql, plan.getDescripcion(),
				plan.getPrecio_mensual(), plan.getCalidad_video(), plan.getResolucion(),
				plan.getCantidad_dispositivos(), id);
	}
}
