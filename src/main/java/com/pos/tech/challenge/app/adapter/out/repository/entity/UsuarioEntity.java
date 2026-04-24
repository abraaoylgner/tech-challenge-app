package com.pos.tech.challenge.app.adapter.out.repository.entity;

import com.pos.tech.challenge.app.adapter.in.controller.request.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    private LocalDateTime dataUltimaAlteracao;

    public UsuarioEntity(UsuarioRequestDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.login = dto.login();
        this.senha = dto.senha();
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}