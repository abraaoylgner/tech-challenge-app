package com.pos.tech.challenge.app.infra;

import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;
import com.pos.tech.challenge.app.core.usecase.UsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

    @Bean
    public UsuarioInputPort criarUsuarioInputPort(UsuarioOutputPort usuarioOutputPort) {

        return new UsuarioUseCase(usuarioOutputPort);
    }
}
