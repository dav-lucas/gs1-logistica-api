package com.codart.logistica.gs1.controller;

import com.codart.logistica.gs1.model.Usuario;
import com.codart.logistica.gs1.repository.UsuarioRepository;
import com.codart.logistica.gs1.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "B. Autenticação", description = "Endpoints para login e geração de Token JWT")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    @Operation(summary = "Realizar Login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos!");
        }

        boolean senhaValida = passwordEncoder.matches(request.getSenha(), usuario.getSenha());

        if (!senhaValida) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos!");
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return ResponseEntity.ok(new LoginResponse(token, usuario.getNome()));
    }
}

class LoginRequest {
    @Schema(example = "exemplo@fiap.com")
    private String email;
    @Schema(example = "senhaSegura123")
    private String senha;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

class LoginResponse {
    private String token;
    private String nome;

    public LoginResponse(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }

    public String getToken() { return token; }
    public String getNome() { return nome; }
}