package mx.tecnm.piedad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.piedad.models.Cuentas;

@Repository
public class CuentasJDBC {
	@Autowired
	JdbcTemplate conexion;
	
	
	public int insertar(int planes_id,Cuentas nueva_cuenta) {
		String sql = "INSERT INTO cuentas(email, password, activa, fecha_ultimo_pago, nombre, apellido, numero_tarjeta, fecha_vencimiento, codigo_seguridad,tipo_tarjeta, planes_id) VALUES (?, ?, 1, NOW(), ?, ?, ?, ?, ?, ?, ?)";
		conexion.update(sql, 
				nueva_cuenta.getEmail(), 
				nueva_cuenta.getPassword(), 
				nueva_cuenta.getNombre(), 
				nueva_cuenta.getApellido(), 
				nueva_cuenta.getNumero_tarjeta(), 
				nueva_cuenta.getFecha_vencimiento(), 
				nueva_cuenta.getCodigo_seguridad(), 
				nueva_cuenta.getTipo_tarjeta(),
				planes_id);
		sql = "SELECT LAST_INSERT_ID()";
		return conexion.queryForObject(sql, Integer.class);
	}

	public void modificarC(int cuenta_id, Cuentas cuenta) {
        String sql = "UPDATE cuentas SET email = ?, password = ?, activa = ?, nombre = ?, "
                        + "apellido = ?, numero_tarjeta = ?, fecha_vencimiento = ?, codigo_seguridad = ?, tipo_tarjeta = ? WHERE id = ?;";
        conexion.update(sql,
                        cuenta.getEmail(),
                        cuenta.getPassword(),
                        cuenta.getActiva(),
                        cuenta.getNombre(),
                        cuenta.getApellido(),
                        cuenta.getNumero_tarjeta(),
                        cuenta.getFecha_vencimiento(),
                        cuenta.getCodigo_seguridad(),
                        cuenta.getTipo_tarjeta(),
                        cuenta_id);
}
	
	public void modificarPC(int plan_id, int cuenta_id) {
        String sql = "UPDATE cuentas SET planes_id = ? WHERE id = ?";
        conexion.update(sql,
                        plan_id,
                        cuenta_id);
	}
	
	public void desactivar(int cuenta_id) {
	        String sql = "UPDATE cuentas SET activa = 0 WHERE id = ?";
	        conexion.update(sql,
	                        cuenta_id);
	}
	
	public void login(String email, String password) {
	        String sql = "SELECT * from cuentas WHERE email = ? and password = ?;";
	        conexion.update(sql,
	                        email,
	                        password);
	}
}
