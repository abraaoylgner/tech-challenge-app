package com.pos.tech.challenge.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String login,
        @NotBlank String senha,
        @NotBlank String endereco,
        boolean ehDonoRestaurante
) {}

