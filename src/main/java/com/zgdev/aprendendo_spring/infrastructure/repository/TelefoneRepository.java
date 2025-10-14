package com.zgdev.aprendendo_spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zgdev.aprendendo_spring.infrastructure.entity.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
