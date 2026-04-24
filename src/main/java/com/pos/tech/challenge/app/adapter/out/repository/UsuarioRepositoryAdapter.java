package com.pos.tech.challenge.app.adapter.out.repository;

import com.pos.tech.challenge.app.adapter.out.repository.entity.UsuarioEntity;
import com.pos.tech.challenge.app.adapter.out.repository.mapper.UsuarioEntityMapper;
import com.pos.tech.challenge.app.core.domain.Usuario;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioOutputPort {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public Usuario salvar(Usuario usuario) {

        var usuarioEntity = usuarioEntityMapper.toEntity(usuario);

        var entidadeSalva = usuarioRepository.save(usuarioEntity);

        return usuarioEntityMapper.toDomain(entidadeSalva);
    }
}
