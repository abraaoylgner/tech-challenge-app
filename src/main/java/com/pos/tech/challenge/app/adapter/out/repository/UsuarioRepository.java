package com.pos.tech.challenge.app.adapter.out.repository;

import com.pos.tech.challenge.app.adapter.out.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
        Optional<UsuarioEntity> findByLogin(String login);
        boolean existsByLogin(String login);
}