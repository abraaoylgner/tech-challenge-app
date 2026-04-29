package com.pos.tech.challenge.app.core.port.in;

import com.pos.tech.challenge.app.core.domain.Usuario;

import java.util.List;

public interface UsuarioInputPort {

    Usuario criar(Usuario usuario);
    Usuario atualizar(Long id, Usuario usuario);
    List<Usuario> buscarPorNome(String nome);
    void excluir(Long id);

    void alterarSenha(Long id, String novaSenha);
}
