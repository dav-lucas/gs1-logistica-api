package com.codart.logistica.gs1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Column(nullable = false)
    @Schema(example = "Transporte de 50 paletes de insumos logísticos")
    private String descricao;

    @Column(nullable = false)
    @Schema(example = "PENDENTE")
    private String status;

    @Column(nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(hidden = true)
    private Usuario usuario;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<Rastreamento> rastreamentos;

}
