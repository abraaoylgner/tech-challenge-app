package com.pos.tech.challenge.app.core.domain;

import java.time.LocalDateTime;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Endereco endereco;
    private LocalDateTime dataUltimaAlteracao;

    public Usuario(Long id, String nome, String email, String login, String senha, TipoUsuario tipoUsuario, Endereco endereco, LocalDateTime dataUltimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.endereco = endereco;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
