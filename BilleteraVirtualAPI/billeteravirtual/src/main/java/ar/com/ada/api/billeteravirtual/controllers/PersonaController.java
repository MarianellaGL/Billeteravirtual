package ar.com.ada.api.billeteravirtual.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.billeteravirtual.entities.Persona;
import ar.com.ada.api.billeteravirtual.services.PersonaService;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaservice;

    @GetMapping ("/personas")

    public List<Persona>getPersonas(){

        List<Persona>  lp = personaservice.getPersonas();

        return lp;



    }

    @GetMapping("/personas/{id}")
    public Persona getPersonaById(@PathVariable int id){


        Persona p = personaservice.buscarPorId(id);

        return p;
    }

    



    
}