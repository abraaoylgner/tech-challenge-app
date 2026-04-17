package com.pos.tech.challenge.app.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        String endereco,
        LocalDateTime dataUltimaAlteracao
) {}
