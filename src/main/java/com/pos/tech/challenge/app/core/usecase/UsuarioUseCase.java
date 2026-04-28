package com.pos.tech.challenge.app.core.usecase;

import com.pos.tech.challenge.app.core.domain.Usuario;
import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;

import java.time.LocalDateTime;

public class UsuarioUseCase implements UsuarioInputPort {

    private final UsuarioOutputPort usuarioOutputPort;

    public UsuarioUseCase(UsuarioOutputPort usuarioOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public Usuario criar(Usuario usuario) {
        return usuarioOutputPort.salvar(usuario);
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
        usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
        usuarioExistente.setDataUltimaAlteracao(LocalDateTime.now());

        return usuarioOutputPort.salvar(usuarioExistente);
    }

    @Override
    public void excluir(Long id) {
        Usuario usuarioExistente = usuarioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuarioOutputPort.excluir(usuarioExistente.getId());
    }

    @Override
    public void alterarSenha(Long id, String novaSenha) {
        Usuario usuarioExistente = usuarioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuarioExistente.setSenha(novaSenha);
        usuarioExistente.setDataUltimaAlteracao(LocalDateTime.now());

        usuarioOutputPort.salvar(usuarioExistente);
    }
}
