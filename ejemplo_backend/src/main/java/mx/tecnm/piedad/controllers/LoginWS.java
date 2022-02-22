package mx.tecnm.piedad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.piedad.dao.LoginJDBC;
import mx.tecnm.piedad.models.Login;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class LoginWS {
	@Autowired
    LoginJDBC repo;

    @PostMapping
    public ResponseEntity<?> iniciarSesion(@RequestBody Login login) {
        try {
            Login resultado = repo.consultarUsuario(login);
            return new ResponseEntity<Login>(resultado, HttpStatus.OK);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
