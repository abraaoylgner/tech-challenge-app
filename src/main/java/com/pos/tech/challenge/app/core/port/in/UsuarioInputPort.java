package com.pos.tech.challenge.app.core.port.in;

import com.pos.tech.challenge.app.core.domain.Usuario;

public interface UsuarioInputPort {

    Usuario criar(Usuario usuario);
    Usuario atualizar(Long id, Usuario usuario);
    void excluir(Long id);

    void alterarSenha(Long id, String novaSenha);
}
