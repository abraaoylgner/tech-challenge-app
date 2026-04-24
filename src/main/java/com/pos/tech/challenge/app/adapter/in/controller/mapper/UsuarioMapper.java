package com.pos.tech.challenge.app.adapter.in.controller.mapper;

import com.pos.tech.challenge.app.adapter.in.controller.request.UsuarioRequestDTO;
import com.pos.tech.challenge.app.adapter.in.controller.response.UsuarioResponseDTO;
import com.pos.tech.challenge.app.core.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "dataUltimaAlteracao", expression = "java(LocalDateTime.now())")
    Usuario toDomain(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO toDto(Usuario usuario);

}
