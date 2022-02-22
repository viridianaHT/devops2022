package mx.tecnm.piedad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.piedad.dao.CuentasJDBC;
import mx.tecnm.piedad.dao.PlanesJDBC;
import mx.tecnm.piedad.models.Cuentas;
import mx.tecnm.piedad.models.Planes;

@RestController
@RequestMapping ("/planes")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class PlanesWS {
	@Autowired
	PlanesJDBC repo;
	
	@Autowired
	CuentasJDBC repoC;
	
	@GetMapping
	public ResponseEntity<?> consultarPlanes() {
		List<Planes> resultado = repo.consultar();
		return new ResponseEntity<List<Planes>>(resultado, HttpStatus.OK);
		
	}
	
	@GetMapping("/{plan-id}")
	public ResponseEntity<?> buscarPlanes(@PathVariable ("plan-id") int id){
		try {
			Planes resultado = repo.buscar(id);
			return new ResponseEntity<Planes>(resultado, HttpStatus.OK);
		}catch (DataAccessException e)
		{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/{plan-id}/cuentas")
    public ResponseEntity<?> insertarCuenta(@PathVariable("plan-id") int planId, @RequestBody Cuentas nueva_cuenta) {
        try {
            int id = repoC.insertar(planId, nueva_cuenta);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
	

    @PutMapping("/{plan-id}/cuentas/{cuenta-id}")
    public ResponseEntity<?> modificarPlan(@PathVariable("plan-id") int planId, @PathVariable("cuenta-id") int cuentaId) {
        repoC.modificarPC(planId, cuentaId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
