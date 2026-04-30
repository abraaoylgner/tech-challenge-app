package com.pos.tech.challenge.app.adapter.in.controller;

import com.pos.tech.challenge.app.adapter.in.controller.request.LoginRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.TokenResponseDTO;
import com.pos.tech.challenge.app.core.port.out.SenhaCriptoOutputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;
import com.pos.tech.challenge.app.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Operações de login e geração de tokens de acesso (JWT)")
public class AuthController {

    private final UsuarioOutputPort usuarioOutputPort;
    private final SenhaCriptoOutputPort senhaCriptoOutputPort;
    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Realizar login (Autenticação)", description = "Verifica as credenciais do usuário (login e senha). Se válidas, retorna um token JWT para acesso aos endpoints protegidos. Endpoint público (não requer token).")
    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso. Token JWT gerado.")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(hidden = true)))
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