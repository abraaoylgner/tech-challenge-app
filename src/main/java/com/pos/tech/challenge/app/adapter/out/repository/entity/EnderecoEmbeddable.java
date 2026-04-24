package com.pos.tech.challenge.app.adapter.out.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class EnderecoEmbeddable {
    private String rua;
    private String numero;
    private String cidade;
    private String cep;
}
