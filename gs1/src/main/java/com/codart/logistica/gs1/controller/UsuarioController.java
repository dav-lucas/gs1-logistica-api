package com.codart.logistica.gs1.controller;

import com.codart.logistica.gs1.model.Usuario;
import com.codart.logistica.gs1.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "A. Usuários", description = "Cadastro de novos utilizadores do sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar Usuários")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Usuário")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario){
        try {
            Usuario novoUsuario = usuarioService.cadastrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
