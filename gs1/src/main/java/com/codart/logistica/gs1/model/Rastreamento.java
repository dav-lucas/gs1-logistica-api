package com.codart.logistica.gs1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_rastreamento")
public class Rastreamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(example = "-23.550520")
    private double latitude;

    @Schema(example = "-46.633308")
    private double longitude;

    @Schema(example = "80.5")
    private double velocidade;

    @Schema(example = "75.0")
    private double nivelCombustivel;

    @Schema(example = "5G")
    private String tipoConexao;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataHoraLeitura;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @Schema(hidden = true)
    private Pedido pedido;
}
