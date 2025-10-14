package com.zgdev.aprendendo_spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zgdev.aprendendo_spring.infrastructure.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
