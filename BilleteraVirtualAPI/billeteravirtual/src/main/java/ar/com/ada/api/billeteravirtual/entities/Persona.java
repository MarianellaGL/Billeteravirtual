package ar.com.ada.api.billeteravirtual.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.ada.api.billeteravirtual.excepciones.PersonaEdadException;


/**
 * Persona
 */
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @Column(name = "persona_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personaId;
    private String nombre;
    private String dni;
    private Integer edad;
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Billetera billetera;
    //@Column(name = "billetera_id")
    //private int billeteraId;
    //@JoinColumn(name= "persona_id", referencedColumnName = "persona_id")
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Usuario usuario;

    public Persona(String nombre, String dni, Integer edad, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.email = email;
    }

    public Persona() {
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {

        this.dni = dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) throws PersonaEdadException {
        if(edad < 18)
        {
            //no se ejecuta nada mas despues del throw
            throw new PersonaEdadException(this, "ocurrio un error con la edad");


        }
        this.edad = edad;
    }
    
 

 

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


   /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.usuario.setPersona(this); //Vinculamos ambos objetos entre si
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
        this.billetera.setPersona(this);
    }

    @JsonIgnore
    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    @JsonIgnore

    public Billetera getBilletera() {
        return billetera;
    }

    



 
    
}