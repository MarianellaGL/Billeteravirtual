package ar.com.ada.api.billeteravirtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.repo.MovimientoRepository;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository repo;
    
}
