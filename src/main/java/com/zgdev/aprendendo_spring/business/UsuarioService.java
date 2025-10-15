package com.zgdev.aprendendo_spring.business;

import com.zgdev.aprendendo_spring.infrastructure.entity.Usuario;
import com.zgdev.aprendendo_spring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvaUsuario(Usuario usuario){
        try {

            return usuarioRepository.save(usuario);
        } catch (){

        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
