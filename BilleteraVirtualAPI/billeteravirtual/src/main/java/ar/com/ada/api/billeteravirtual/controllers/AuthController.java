package ar.com.ada.api.billeteravirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.billeteravirtual.excepciones.PersonaDNIException;
import ar.com.ada.api.billeteravirtual.excepciones.PersonaEdadException;
import ar.com.ada.api.billeteravirtual.models.request.RegistrationRequest;
import ar.com.ada.api.billeteravirtual.models.response.RegistrationResponse;
import ar.com.ada.api.billeteravirtual.services.UsuarioService;

/**
 * AuthController
 */
@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("auth/register")
    public RegistrationResponse postRegisterUser(@RequestBody RegistrationRequest req)
            throws PersonaEdadException, PersonaDNIException{
        RegistrationResponse r = new RegistrationResponse();
        //aca creamos la persona y el usuario a traves del service.

        int usuarioCreadoId = usuarioService.alta(req.fullName, req.dni, req.email, req.userName, req.edad,
                req.password, req.moneda, req. userEmail);

        r.isOk = true;
        r.message = "Te registraste con exitoooo";
        r.usuarioId = usuarioCreadoId;
    
        return r;

    }

    
    
}