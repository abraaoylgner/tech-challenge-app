package com.pos.tech.challenge.app.adapter.in.controller;

import com.pos.tech.challenge.app.adapter.in.controller.mapper.UsuarioMapper;
import com.pos.tech.challenge.app.adapter.in.controller.request.AlterarSenhaRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.request.UsuarioRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.UsuarioResponseDTO;
import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioInputPort usuarioInputPort;

    @Autowired
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        var responseInputPort = usuarioInputPort.criar(mapper.toDomain(usuarioRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(responseInputPort));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNome(
            @RequestParam(required = true) String nome) {

        var usuarios = usuarioInputPort.buscarPorNome(nome);

        var response = usuarios.stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {

        var usuarioDomain = mapper.toDomain(usuarioRequestDTO);

        var usuarioAtualizado = usuarioInputPort.atualizar(id, usuarioDomain);

        return ResponseEntity.ok(mapper.toDto(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioInputPort.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @RequestBody @Valid AlterarSenhaRequestDTO request) {

        usuarioInputPort.alterarSenha(id, request.novaSenha());
        return ResponseEntity.noContent().build();
    }


}
