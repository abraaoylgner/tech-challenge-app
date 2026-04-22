package com.pos.tech.challenge.app.entity;

import com.pos.tech.challenge.app.dto.UsuarioRequestDTO;
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
public class Usuario {
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

    private String endereco;

    private boolean ehDonoRestaurante;

    private LocalDateTime dataUltimaAlteracao;

    // Construtor para converter DTO em Entidade
    public Usuario(UsuarioRequestDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.login = dto.login();
        this.senha = dto.senha();
        this.endereco = dto.endereco();
        this.ehDonoRestaurante = dto.ehDonoRestaurante();
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}