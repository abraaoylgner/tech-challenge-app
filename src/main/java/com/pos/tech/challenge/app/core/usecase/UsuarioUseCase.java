package com.pos.tech.challenge.app.core.usecase;

import com.pos.tech.challenge.app.core.domain.Usuario;
import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import com.pos.tech.challenge.app.core.port.out.SenhaCriptoOutputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioUseCase implements UsuarioInputPort {

    private final UsuarioOutputPort usuarioOutputPort;
    private final SenhaCriptoOutputPort senhaCriptoOutputPort;

    public UsuarioUseCase(UsuarioOutputPort usuarioOutputPort, SenhaCriptoOutputPort senhaCriptoOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.senhaCriptoOutputPort = senhaCriptoOutputPort;
    }

    @Override
    public Usuario criar(Usuario usuario) {
        String senhaCriptografada = senhaCriptoOutputPort.criptografar(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

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
    public List<Usuario> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca não pode estar vazio.");
        }
        return usuarioOutputPort.buscarPorNome(nome);
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
