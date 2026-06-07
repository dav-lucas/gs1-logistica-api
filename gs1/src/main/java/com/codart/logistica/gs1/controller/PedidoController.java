package com.codart.logistica.gs1.controller;

import com.codart.logistica.gs1.model.Pedido;
import com.codart.logistica.gs1.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "C. Pedidos", description = "Gestão e criação de pedidos de logística")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar Pedidos")
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PostMapping("/usuarios/{usuarioId}")
    @Operation(summary = "Postar Pedido")
    public ResponseEntity<?> criar(@RequestBody Pedido pedido, @PathVariable UUID usuarioId) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedido, usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar Status")
    public ResponseEntity<?> atualizarStatus(@PathVariable UUID id, @RequestBody StatusRequest request){
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, request.getNovoStatus());
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Pedido")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        try {
            pedidoService.deletarPedido(id);
            return ResponseEntity.ok("Pedido deletado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class StatusRequest {

        @Schema(example = "EM_TRANSITO")
        private String novoStatus;
        }
    }
