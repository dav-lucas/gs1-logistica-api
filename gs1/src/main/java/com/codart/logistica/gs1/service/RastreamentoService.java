package com.codart.logistica.gs1.service;

import com.codart.logistica.gs1.model.Pedido;
import com.codart.logistica.gs1.model.Rastreamento;
import com.codart.logistica.gs1.repository.PedidoRepository;
import com.codart.logistica.gs1.repository.RastreamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RastreamentoService {

    @Autowired
    private RastreamentoRepository rastreamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Rastreamento registrarPonto(Rastreamento rastreamento, UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

        if (pedido == null) {
            throw new IllegalArgumentException("Não é possível rastrear: Pedido não encontrado.");
        }

        rastreamento.setPedido(pedido);

        rastreamento.setDataHoraLeitura(LocalDateTime.now());

        return rastreamentoRepository.save(rastreamento);
    }
}
