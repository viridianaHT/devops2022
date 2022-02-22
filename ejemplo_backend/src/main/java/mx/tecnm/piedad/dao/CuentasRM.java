package mx.tecnm.piedad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.piedad.models.Cuentas;

public class CuentasRM implements RowMapper<Cuentas>{
	@Override
	public Cuentas mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cuentas cuentas = new Cuentas();
		cuentas.setId(rs.getInt("id"));
        cuentas.setEmail(rs.getString("email"));
        cuentas.setPassword(rs.getString("password"));
        cuentas.setActiva(rs.getInt("activa"));
        cuentas.setFecha_ultimo_pago(rs.getDate("fecha_ultimo_pago"));
        cuentas.setNombre(rs.getString("nombre"));
        cuentas.setApellido(rs.getString("apellido"));
        cuentas.setNumero_tarjeta(rs.getString("numero_tarjeta"));
        cuentas.setFecha_vencimiento(rs.getString("fecha_vencimiento"));
        cuentas.setCodigo_seguridad(rs.getString("codigo_seguridad"));
        cuentas.setTipo_tarjeta(rs.getString("tipo_tarjeta"));
        cuentas.setPlanes_id(rs.getInt("planes_id"));
        return cuentas;
	}


}
