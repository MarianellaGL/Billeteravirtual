package ar.com.ada.api.billeteravirtual.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Usuario
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;
    @Column(name = "username")
    private String userName;
    private String password;
    @Column(name = "email")
    private String userEmail;
    @OneToOne
    @JoinColumn(name= "persona_id", referencedColumnName = "persona_id")
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

    @JsonIgnore
    //nunca se expone la contraseña

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [User Name=" + userName + ", Password=" + password + ", User Email=" + userEmail + "]";
    }




  
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {
        return userEmail;
    }

    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    @JsonIgnore
    public Persona getPersona() {
        return persona;
    }

    public void setPersonaId(int personaId) {
	}

   
}