package com.codart.logistica.gs1.controller;

import com.codart.logistica.gs1.model.Rastreamento;
import com.codart.logistica.gs1.repository.RastreamentoRepository;
import com.codart.logistica.gs1.service.RastreamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rastreamentos")
@Tag(name = "D. Rastreamento de Carga", description = "Monitoramento de rotas")
public class RastreamentoController {

    @Autowired
    private RastreamentoService rastreamentoService;

    @Autowired
    private RastreamentoRepository rastreamentoRepository;

    @PostMapping("/pedidos/{pedidoId}")
    @Operation(summary = "Postar Rastreamento")
    public ResponseEntity<?> registrarPonto(@RequestBody Rastreamento rastreamento, @PathVariable UUID pedidoId) {
        try {
            Rastreamento novoPonto = rastreamentoService.registrarPonto(rastreamento, pedidoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPonto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pedidos/{pedidoId}")
    @Operation(summary = "Listar Rastreamentos")
    public ResponseEntity<List<Rastreamento>> obterHistorico(@PathVariable UUID pedidoId) {
        List<Rastreamento> historico = rastreamentoRepository.findByPedidoIdOrderByDataHoraLeituraDesc(pedidoId);
        return ResponseEntity.ok(historico);
    }
}
