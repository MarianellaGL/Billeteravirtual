package ar.com.ada.api.billeteravirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Persona;
import ar.com.ada.api.billeteravirtual.repo.PersonaRepository;

/**
 * PersonaService aca va logica de negocio//
 */
@Service
public class PersonaService {

    @Autowired
    PersonaRepository repo;

    public void save(Persona p){

        repo.save(p);
    }

    public List<Persona> getPersonas() {

        return repo.findAll();
    }

    public Persona buscarPorNombre(String nombre) {

        return repo.findByNombre(nombre);
    }
 
    
    public Persona buscarPorDni(String dni) {

        return repo.findByDni(dni);
    }

    public Persona buscarPorId(int id) {

        Optional<Persona> p = repo.findById(id);
        
        if (p.isPresent())
            return p.get();
        return null;
    }

}

