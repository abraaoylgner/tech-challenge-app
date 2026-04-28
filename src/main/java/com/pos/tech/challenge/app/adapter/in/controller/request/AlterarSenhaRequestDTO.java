package com.pos.tech.challenge.app.adapter.in.controller.request;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaRequestDTO(
        @NotBlank String novaSenha
) {
}
