package com.pos.tech.challenge.app.infra.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.pos.tech.challenge.app.core.port.out.SenhaCriptoOutputPort;
import org.springframework.stereotype.Component;

@Component
public class BCryptSenhaAdapter implements SenhaCriptoOutputPort {

    private final PasswordEncoder passwordEncoder;

    public BCryptSenhaAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String criptografar(String senha) {
        return passwordEncoder.encode(senha);
    }

    @Override
    public boolean matches(String senhaEmTexto, String senhaCriptografada) {
        return passwordEncoder.matches(senhaEmTexto, senhaCriptografada);
    }

    @org.springframework.context.annotation.Bean
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}