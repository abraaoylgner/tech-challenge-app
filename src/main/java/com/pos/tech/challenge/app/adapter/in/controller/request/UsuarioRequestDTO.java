package com.pos.tech.challenge.app.adapter.in.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String login,
        @NotBlank String senha
) {}

