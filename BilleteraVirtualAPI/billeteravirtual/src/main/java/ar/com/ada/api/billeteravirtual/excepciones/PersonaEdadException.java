package ar.com.ada.api.billeteravirtual.excepciones;

import ar.com.ada.api.billeteravirtual.entities.Persona;

public class PersonaEdadException extends PersonaInfoException {

    public PersonaEdadException(Persona p, String mensaje) {
        super(p, mensaje);
    }

    
}