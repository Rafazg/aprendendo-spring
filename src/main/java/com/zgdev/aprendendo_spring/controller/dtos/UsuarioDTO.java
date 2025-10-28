package com.zgdev.aprendendo_spring.controller.dtos;

import com.zgdev.aprendendo_spring.infrastructure.entity.Endereco;
import com.zgdev.aprendendo_spring.infrastructure.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private List<Endereco> enderecos;


    public UsuarioDTO(String nome, String email, List<Endereco> enderecos) {
        this.nome = nome;
        this.email = email;
        this.enderecos = enderecos;
    }

    public UsuarioDTO(String senha) {
        this.senha = senha;
    }
}
