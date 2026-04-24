package com.pos.tech.challenge.app.core.port.in;

import com.pos.tech.challenge.app.adapter.in.controller.response.UsuarioResponseDTO;
import com.pos.tech.challenge.app.core.domain.Usuario;

public interface UsuarioInputPort {

    Usuario criar(Usuario usuario);
}
