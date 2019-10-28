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
    private String username;
    private String password;
    @Column(name = "email")
    private String userEmail;
    @OneToOne
    @JoinColumn(name= "persona_id", referencedColumnName = "persona_id")
    private Persona persona;
  

    public Usuario (String username, String password, String email){
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    //nunca se expone la contraseña

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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