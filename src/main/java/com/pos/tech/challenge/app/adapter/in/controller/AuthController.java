package com.pos.tech.challenge.app.adapter.in.controller;

import com.pos.tech.challenge.app.adapter.in.controller.request.LoginRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.TokenResponseDTO;
import com.pos.tech.challenge.app.core.port.out.SenhaCriptoOutputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;
import com.pos.tech.challenge.app.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioOutputPort usuarioOutputPort;
    private final SenhaCriptoOutputPort senhaCriptoOutputPort;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        var usuario = usuarioOutputPort.buscarPorLogin(data.login())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (senhaCriptoOutputPort.matches(data.senha(), usuario.getSenha())) {
            String token = tokenService.gerarToken(usuario);
            return ResponseEntity.ok(new TokenResponseDTO(token));
        }

        return ResponseEntity.status(401).build();
    }
}