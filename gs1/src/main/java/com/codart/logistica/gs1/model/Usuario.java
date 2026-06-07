package com.codart.logistica.gs1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Column(nullable = false)
    @Schema(example = "Exemplo")
    private String nome;

    @Column(nullable = false, unique = true)
    @Schema(example = "exemplo@fiap.com")
    private String email;

    @Column(nullable = false)
    @Schema(example = "senhaSegura123")
    private String senha;

}
