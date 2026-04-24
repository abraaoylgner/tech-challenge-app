package com.pos.tech.challenge.app.adapter.in.controller.request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(
        @NotBlank String rua,
        @NotBlank String numero,
        @NotBlank String cidade,
        @NotBlank String cep
        ) { }

