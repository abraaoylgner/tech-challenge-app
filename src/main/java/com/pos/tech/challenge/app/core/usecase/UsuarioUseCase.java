package com.pos.tech.challenge.app.core.usecase;

import com.pos.tech.challenge.app.core.domain.Usuario;
import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;

public class UsuarioUseCase implements UsuarioInputPort {

    private final UsuarioOutputPort usuarioOutputPort;

    public UsuarioUseCase(UsuarioOutputPort usuarioOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public Usuario criar(Usuario usuario) {

        return usuarioOutputPort.salvar(usuario);
    }
}
