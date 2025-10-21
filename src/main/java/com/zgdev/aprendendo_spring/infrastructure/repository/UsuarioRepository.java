package com.zgdev.aprendendo_spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zgdev.aprendendo_spring.infrastructure.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
