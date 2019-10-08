package ar.com.ada.api.billeteravirtual.excepciones;

import ar.com.ada.api.billeteravirtual.entities.Persona;

public class PersonaDNIException extends PersonaInfoException {

    public PersonaDNIException(Persona p, String mensaje) {
        super(p, mensaje);
    }

    
}