package com.codart.logistica.gs1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private UUID id;

    private double latitude;
    private double longitude;
    private double velocidade;
    private double nivelCombustivel;
    private String tipoConexao;
    private LocalDateTime dataHoraLeitura;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
}
