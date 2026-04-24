package com.pos.tech.challenge.app.adapter.in.controller;

import com.pos.tech.challenge.app.adapter.in.controller.mapper.UsuarioMapper;
import com.pos.tech.challenge.app.adapter.in.controller.request.UsuarioRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.UsuarioResponseDTO;
import com.pos.tech.challenge.app.core.port.in.UsuarioInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
