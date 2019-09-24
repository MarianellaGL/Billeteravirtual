package ar.com.ada.billeteravirtual.excepciones;

import ar.com.ada.billeteravirtual.Persona;

public class PersonaInfoException extends Exception {

    private Persona persona;
    public PersonaInfoException(Persona p, String mensaje) {
        
        super(p.getNombre()+":"+ mensaje);
        this.persona = p;
    }
}
