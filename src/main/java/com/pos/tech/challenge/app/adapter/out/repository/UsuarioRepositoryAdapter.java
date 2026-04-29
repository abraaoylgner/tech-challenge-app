package com.pos.tech.challenge.app.adapter.out.repository;

import com.pos.tech.challenge.app.adapter.out.repository.entity.UsuarioEntity;
import com.pos.tech.challenge.app.adapter.out.repository.mapper.UsuarioEntityMapper;
import com.pos.tech.challenge.app.core.domain.Usuario;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .map(usuarioEntityMapper::toDomain);
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(usuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void excluir(Long id){
        usuarioRepository.deleteById(id);
    }
}
