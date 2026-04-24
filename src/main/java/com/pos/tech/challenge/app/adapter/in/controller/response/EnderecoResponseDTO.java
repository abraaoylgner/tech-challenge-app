package com.pos.tech.challenge.app.adapter.in.controller.response;

public record EnderecoResponseDTO(
        String rua,
        String numero,
        String cidade,
        String cep
) {}
