package com.pos.tech.challenge.app.core.port.out;

import com.pos.tech.challenge.app.core.domain.Usuario;

import java.util.Optional;

public interface UsuarioOutputPort {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorLogin(String login);
    void excluir(Long id);
}
