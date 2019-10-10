package ar.com.ada.api.billeteravirtual.controllers;

import ar.com.ada.api.billeteravirtual.entities.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.billeteravirtual.services.*;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioservice;

    @GetMapping("/personas/usuarios")

    public List<Usuario>getUsuarios(){

        List<Usuario>  us = usuarioservice.getUsuarios();

        return us;

    }

    @GetMapping("/personas/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable int id){


        Usuario u = usuarioservice.buscarPorId(id);

        return u;
    }

    





    
}