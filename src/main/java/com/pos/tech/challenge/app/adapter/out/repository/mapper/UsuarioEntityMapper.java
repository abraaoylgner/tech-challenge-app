package com.pos.tech.challenge.app.adapter.out.repository.mapper;

import com.pos.tech.challenge.app.adapter.out.repository.entity.UsuarioEntity;
import com.pos.tech.challenge.app.core.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity usuarioEntity);
}
