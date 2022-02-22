package mx.tecnm.piedad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.piedad.dao.PerfilesJDBC;
import mx.tecnm.piedad.models.Perfiles;

@RestController
@RequestMapping ("/perfiles")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class PerfilesWS {
	
	@Autowired
    PerfilesJDBC repoP;
    
    @PostMapping("/{perfil-id}")
    public ResponseEntity<?> modificarPerfil(@PathVariable("perfil-id") int cuentaId,
            @PathVariable("perfil-id") int perfilId,
            @RequestBody Perfiles perfil) {
        try {
            repoP.modificar(perfilId, cuentaId, perfil);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping("/{perfil-id}")
    public ResponseEntity<?> desactivarPerfil(@PathVariable("perfil-id") int cuentaId,
            @PathVariable("perfil-id") int perfilId) {
        repoP.desactivar(cuentaId, perfilId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	

}
