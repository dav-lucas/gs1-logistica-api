package com.codart.logistica.gs1.service;

import com.codart.logistica.gs1.model.Usuario;
import com.codart.logistica.gs1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario cadastrar(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente != null){
            throw new IllegalArgumentException("Este e-mail já foi cadastrado!");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }
}
