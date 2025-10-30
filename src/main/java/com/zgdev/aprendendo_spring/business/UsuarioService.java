package com.zgdev.aprendendo_spring.business;

import com.zgdev.aprendendo_spring.controller.dtos.UsuarioDTO;
import com.zgdev.aprendendo_spring.infrastructure.entity.Usuario;
import com.zgdev.aprendendo_spring.infrastructure.exceptions.ConflictException;
import com.zgdev.aprendendo_spring.infrastructure.exceptions.ResourceNotFoundException;
import com.zgdev.aprendendo_spring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public Usuario salvaUsuario(Usuario usuario){
        try {
            // código que pode gerar uma exceção
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);

        } catch (ConflictException e){
            // o que fazer se essa exceção ocorrer
            throw new ConflictException("Email já cadastrado");
        }
    }


    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }


    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }


    public Usuario buscarUsuarioPorEmail(String email){
       return usuarioRepository.findByEmail(email).orElseThrow(
               () -> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
         usuarioRepository.deleteByEmail(email);
    }

    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioAtualizado){
        Usuario existente = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado" + id));

        existente.setNome(usuarioAtualizado.getNome());
        existente.setEmail(usuarioAtualizado.getEmail());

        return usuarioRepository.save(existente);
    }
}
