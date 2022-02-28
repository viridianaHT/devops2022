package mx.tecnm.piedad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.piedad.dao.CuentasJDBC;
import mx.tecnm.piedad.dao.PerfilesJDBC;
import mx.tecnm.piedad.models.Cuentas;
import mx.tecnm.piedad.models.Perfiles;

//Hola soy Karen

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class CuentasWS {
	 @Autowired
	    CuentasJDBC repo;

	    @PutMapping("/{cuenta-id}")
	    public ResponseEntity<?> modificarCuenta(@PathVariable("cuenta-id") int cuentaId, @RequestBody Cuentas cuenta) {
	        repo.modificarC(cuentaId, cuenta);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	    @DeleteMapping("/{cuenta-id}")
	    public ResponseEntity<?> desactivarCuenta(@PathVariable("cuenta-id") int cuentaId) {
	        repo.desactivar(cuentaId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }


	    @Autowired
	    PerfilesJDBC repoP;


	    @PostMapping("/{cuenta-id}/perfiles")
	    public ResponseEntity<?> insertarPerfil(@PathVariable("cuenta-id") int cuentaId,
	            @RequestBody Perfiles nuevo_perfil) {
	        try {
	            int id = repoP.insertar(cuentaId, nuevo_perfil);
	            return new ResponseEntity<>(id, HttpStatus.CREATED);
	        } catch (DataAccessException e) {
	            System.out.println(e.getMessage());
	            return new ResponseEntity<>(HttpStatus.CONFLICT);
	        }
	    }

	    @GetMapping("/{cuenta-id}/perfiles")
	    public ResponseEntity<?> consultarPerfiles(@PathVariable("cuenta-id") int cuentaId) {
	        try {
	            List<Perfiles> resultado = repoP.listar(cuentaId);
	            return new ResponseEntity<>(resultado, HttpStatus.OK);
	        } catch (DataAccessException e) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }
}
