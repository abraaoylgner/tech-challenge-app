package com.pos.tech.challenge.app.adapter.in.controller.request;

import com.pos.tech.challenge.app.core.domain.TipoUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull TipoUsuario tipoUsuario,
        @Valid EnderecoRequestDTO endereco
        ) {}

