package com.pos.tech.challenge.app.core.port.out;

import com.pos.tech.challenge.app.core.domain.Usuario;

public interface UsuarioOutputPort {
    Usuario salvar(Usuario usuario);
}
