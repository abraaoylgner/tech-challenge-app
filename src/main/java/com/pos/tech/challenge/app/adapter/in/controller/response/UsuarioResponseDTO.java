package com.pos.tech.challenge.app.adapter.in.controller.response;

import com.pos.tech.challenge.app.core.domain.TipoUsuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        TipoUsuario tipoUsuario,
        EnderecoResponseDTO endereco,
        LocalDateTime dataUltimaAlteracao
) {}
