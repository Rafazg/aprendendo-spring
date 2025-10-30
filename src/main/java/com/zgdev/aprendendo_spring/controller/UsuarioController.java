package com.zgdev.aprendendo_spring.controller;

import com.zgdev.aprendendo_spring.business.UsuarioService;
import com.zgdev.aprendendo_spring.controller.dtos.RecuperaSenhaDTO;
import com.zgdev.aprendendo_spring.controller.dtos.UsuarioDTO;
import com.zgdev.aprendendo_spring.infrastructure.entity.Usuario;
import com.zgdev.aprendendo_spring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }


    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return "Bearer" + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email){
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), usuario.getEmail(), usuario.getEnderecos());
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("/recuperaSenha")
    public ResponseEntity<RecuperaSenhaDTO> verificarSenhaPorEmail(@RequestParam("email") String email){
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        RecuperaSenhaDTO RecuperaSenhaDTO;
        return ResponseEntity.ok(RecuperaSenhaDTO = new RecuperaSenhaDTO(usuario.getSenha()));
    }


    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarDados(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        usuarioService.atualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok().build();
    }

}
