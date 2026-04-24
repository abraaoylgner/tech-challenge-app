package com.pos.tech.challenge.app.adapter.in.controller.response;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        LocalDateTime dataUltimaAlteracao
) {}
