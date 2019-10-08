package ar.com.ada.api.billeteravirtual.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billeteravirtual.entities.*;

@Repository
public interface BilleteraRepository extends JpaRepository<Billetera, Integer> {


    public Billetera findByPersona(Persona p);
    
}
