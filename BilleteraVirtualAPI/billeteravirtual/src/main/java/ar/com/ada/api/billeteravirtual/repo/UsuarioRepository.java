package ar.com.ada.api.billeteravirtual.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billeteravirtual.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByusername(String username);

    public Usuario findByUserEmail(String userEmail);

}