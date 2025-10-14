package com.zgdev.aprendendo_spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zgdev.aprendendo_spring.infrastructure.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


}
