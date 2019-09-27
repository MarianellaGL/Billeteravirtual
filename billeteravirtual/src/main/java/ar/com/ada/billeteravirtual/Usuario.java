package ar.com.ada.billeteravirtual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Usuario
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer usuarioId;
    private String userName;
    private String password;
    @Column (name= "email")
    private String userEmail;
    /*@Column(name = "persona_id")
    private int personaId;*/
    
//ACTIVO lel OneTo One, pero para que funcione persona tiene quetener un valor. Aca no hace falta el cascade
  @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    //join va del lado donde esta puesta la FK
    //@MapsId ese mapea con el que sta ref con el objeto persona
    private Persona persona;


    public Usuario (String userName, String password, String email){
        this.userName = userName;
        this.password = password;
        this.userEmail = email;
    }

    public Usuario(){

    }


    public Usuario (String password){
        this.password = password;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
    @Override
    public String toString() {
        return "Usuario [User Name=" + userName + ", Password=" + password + ", User Email=" + userEmail + "]";
    }
/*
    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public Usuario(int personaId) {
        this.personaId = personaId;
    }*/
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

	public void setPersonaId(int personaId) {
	}

    /*public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/



}