package com.pos.tech.challenge.app.adapter.in.controller;

import com.pos.tech.challenge.app.adapter.in.controller.mapper.UsuarioMapper;
import com.pos.tech.challenge.app.adapter.in.controller.request.AlterarSenhaRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.request.UsuarioRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.UsuarioResponseDTO;
import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas ao gerenciamento de usuários (Clientes e Donos de Restaurante)")
public class UsuarioController {

    private final UsuarioInputPort usuarioInputPort;

    @Autowired
    private final UsuarioMapper mapper;

    @PostMapping
    @Operation(summary = "Criar um novo usuário", description = "Cadastra um novo usuário no sistema. Endpoint público.")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou e-mail/login já existentes", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        var responseInputPort = usuarioInputPort.criar(mapper.toDomain(usuarioRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(responseInputPort));
    }

    @GetMapping
    @Operation(summary = "Buscar usuários por nome", description = "Retorna uma lista de usuários que contenham o nome pesquisado.")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNome(
            @RequestParam(required = true) String nome) {

        var usuarios = usuarioInputPort.buscarPorNome(nome);

        var response = usuarios.stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {

        var usuarioDomain = mapper.toDomain(usuarioRequestDTO);

        var usuarioAtualizado = usuarioInputPort.atualizar(id, usuarioDomain);

        return ResponseEntity.ok(mapper.toDto(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Remove um usuário do sistema utilizando o seu ID")
    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso (Sem conteúdo de retorno)")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioInputPort.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/senha")
    @Operation(summary = "Alterar senha do usuário", description = "Atualiza exclusivamente a senha de um usuário existente")
    @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso (Sem conteúdo de retorno)")
    @ApiResponse(responseCode = "400", description = "Formato de senha inválido", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @RequestBody @Valid AlterarSenhaRequestDTO request) {

        usuarioInputPort.alterarSenha(id, request.novaSenha());
        return ResponseEntity.noContent().build();
    }


}
