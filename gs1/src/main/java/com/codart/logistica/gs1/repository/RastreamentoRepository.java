package com.codart.logistica.gs1.repository;

import com.codart.logistica.gs1.model.Rastreamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RastreamentoRepository extends JpaRepository<Rastreamento, UUID> {
    List<Rastreamento> findByPedidoIdOrderByDataHoraLeituraDesc(UUID pedidoId);
}
