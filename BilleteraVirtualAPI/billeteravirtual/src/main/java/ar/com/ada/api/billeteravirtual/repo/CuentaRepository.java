package ar.com.ada.api.billeteravirtual.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billeteravirtual.entities.*;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

    public Cuenta findByBilletera(Persona p, Billetera b);

    
}