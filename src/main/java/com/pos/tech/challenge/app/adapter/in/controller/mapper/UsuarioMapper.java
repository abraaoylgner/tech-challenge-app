package com.pos.tech.challenge.app.adapter.in.controller.mapper;

import com.pos.tech.challenge.app.adapter.in.controller.request.UsuarioRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.UsuarioResponseDTO;
import com.pos.tech.challenge.app.core.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", expression = "java(LocalDateTime.now())")
    Usuario toDomain(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO toDto(Usuario usuario);

}
