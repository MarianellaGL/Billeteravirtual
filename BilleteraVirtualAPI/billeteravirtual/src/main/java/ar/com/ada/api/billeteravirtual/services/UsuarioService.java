package ar.com.ada.api.billeteravirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ada.api.billeteravirtual.repo.UsuarioRepository;
import ar.com.ada.api.billeteravirtual.security.Crypto;
import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.excepciones.PersonaDNIException;
import ar.com.ada.api.billeteravirtual.excepciones.PersonaEdadException;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    BilleteraService bs;

    @Autowired
    PersonaService ps;

    @PostMapping("auth/register")
    public int alta(String fullName, String dni, String email, String userName, int edad, String password, String moneda,
            String userEmail) throws PersonaEdadException,PersonaDNIException{
        Persona p = new Persona();
        p.setNombre(fullName);
        p.setDni(dni);
        p.setEmail(email);
        p.setEdad(edad);

        p.getPersonaId();

        Usuario u = new Usuario();
        u.setUserName(p.getEmail());
        u.setUserEmail(p.getEmail());

        String passwordEnTextoClaro;
        String passwordEncriptada;
        String passwordEnTextoClaroDesencriptado;

        passwordEnTextoClaro = password;
        passwordEncriptada = Crypto.encrypt(passwordEnTextoClaro, u.getUserName());

        u.setPassword(passwordEncriptada);
        p.setUsuario(u);
        ps.save(p);

        Billetera b = new Billetera(p);
        Cuenta c = new Cuenta(b, moneda);

        c.setBilletera(b);

        bs.save(b);

        return u.getUsuarioId();

    }

    public void save(Usuario u) {
        repo.save(u);
    }

    public List<Usuario> getUsuarios() {

        return repo.findAll();
    }

    public Usuario buscarPorUserName(String userName) {

        return repo.findByuserName(userName);
    }

    public Usuario buscarPorEmail(String email) {

        return repo.findByUserEmail(email);
    }

    public Usuario buscarPorId(int id) {

        Optional<Usuario> u = repo.findById(id);

        if (u.isPresent())
            return u.get();
        return null;
    }

}