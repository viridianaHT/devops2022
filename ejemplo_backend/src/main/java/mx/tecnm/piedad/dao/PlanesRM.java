package mx.tecnm.piedad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.piedad.models.Planes;

public class PlanesRM implements RowMapper<Planes>{
	@Override
	public Planes mapRow(ResultSet rs, int rowNum) throws SQLException {
		Planes planes = new Planes();
		planes.setId(rs.getInt("id"));
		planes.setDescripcion(rs.getString("descripcion"));
		planes.setPrecio_mensual(rs.getBigDecimal("precio_mensual"));
		planes.setCalidad_video(rs.getString("calidad_video"));
		planes.setResolucion(rs.getString("resolucion"));
		planes.setCantidad_dispositivos(rs.getInt("cantidad_dispositivos"));
		return planes;
	}

}
