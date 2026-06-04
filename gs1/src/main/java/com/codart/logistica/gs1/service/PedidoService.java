package com.codart.logistica.gs1.service;

import com.codart.logistica.gs1.model.Pedido;
import com.codart.logistica.gs1.model.Usuario;
import com.codart.logistica.gs1.repository.PedidoRepository;
import com.codart.logistica.gs1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido criarPedido(Pedido pedido, UUID usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado! Não é possível criar o pedido.");
        }

        pedido.setUsuario(usuario);
        pedido.setStatus("PENDENTE");

        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarStatus(UUID id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado! Não foi possível atualizar o status.");
        }

        pedido.setStatus(novoStatus.toUpperCase().trim());
        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(UUID id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado! Não foi possível deletar o pedido.");
        }

        pedidoRepository.delete(pedido);
    }
}
