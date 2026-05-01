package com.pos.tech.challenge.app.core.usecase;

import com.pos.tech.challenge.app.core.domain.TipoUsuario;
import com.pos.tech.challenge.app.core.domain.Usuario;
import com.pos.tech.challenge.app.core.port.out.SenhaCriptoOutputPort;
import com.pos.tech.challenge.app.core.port.out.UsuarioOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private UsuarioOutputPort usuarioOutputPort;

    @Mock
    private SenhaCriptoOutputPort senhaCriptoOutputPort;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        usuarioMock = new Usuario(
                1L, "João da Silva", "joao@email.com", "joaosilva",
                "senha123", TipoUsuario.CLIENTE, null, LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Deve criar usuário com sucesso e criptografar a senha")
    void deveCriarUsuarioComSucesso() {
        when(senhaCriptoOutputPort.criptografar("senha123")).thenReturn("senha_hash");
        when(usuarioOutputPort.salvar(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario resultado = usuarioUseCase.criar(usuarioMock);

        assertNotNull(resultado);
        assertEquals("senha_hash", usuarioMock.getSenha());
        verify(senhaCriptoOutputPort, times(1)).criptografar(anyString());
        verify(usuarioOutputPort, times(1)).salvar(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve atualizar usuário com sucesso quando ele existir")
    void deveAtualizarUsuarioComSucesso() {
        Usuario usuarioAtualizado = new Usuario(
                1L, "João Editado", "joao@email.com", "joaosilva",
                "senha123", TipoUsuario.DONO_RESTAURANTE, null, null
        );

        when(usuarioOutputPort.buscarPorId(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioOutputPort.salvar(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario resultado = usuarioUseCase.atualizar(1L, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("João Editado", usuarioMock.getNome());
        assertEquals(TipoUsuario.DONO_RESTAURANTE, usuarioMock.getTipoUsuario());
        assertNotNull(usuarioMock.getDataUltimaAlteracao());
        verify(usuarioOutputPort, times(1)).buscarPorId(1L);
        verify(usuarioOutputPort, times(1)).salvar(usuarioMock);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar usuário que não existe")
    void deveLancarExcecaoAoAtualizarUsuarioInexistente() {
        when(usuarioOutputPort.buscarPorId(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                usuarioUseCase.atualizar(99L, usuarioMock)
        );

        assertTrue(exception.getMessage().contains("Usuário não encontrado"));
        verify(usuarioOutputPort, never()).salvar(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve buscar usuários por nome com sucesso")
    void deveBuscarPorNomeComSucesso() {
        when(usuarioOutputPort.buscarPorNome("João")).thenReturn(List.of(usuarioMock));

        List<Usuario> resultado = usuarioUseCase.buscarPorNome("João");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("João da Silva", resultado.get(0).getNome());
        verify(usuarioOutputPort, times(1)).buscarPorNome("João");
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar com nome nulo ou vazio")
    void deveLancarExcecaoAoBuscarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> usuarioUseCase.buscarPorNome(null));
        assertThrows(IllegalArgumentException.class, () -> usuarioUseCase.buscarPorNome("   "));

        verify(usuarioOutputPort, never()).buscarPorNome(anyString());
    }

    @Test
    @DisplayName("Deve excluir usuário com sucesso quando ele existir")
    void deveExcluirUsuarioComSucesso() {
        when(usuarioOutputPort.buscarPorId(1L)).thenReturn(Optional.of(usuarioMock));
        doNothing().when(usuarioOutputPort).excluir(1L);

        assertDoesNotThrow(() -> usuarioUseCase.excluir(1L));

        verify(usuarioOutputPort, times(1)).buscarPorId(1L);
        verify(usuarioOutputPort, times(1)).excluir(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar excluir usuário que não existe")
    void deveLancarExcecaoAoExcluirUsuarioInexistente() {
        when(usuarioOutputPort.buscarPorId(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                usuarioUseCase.excluir(99L)
        );

        assertTrue(exception.getMessage().contains("Usuário não encontrado"));
        verify(usuarioOutputPort, never()).excluir(anyLong());
    }

    @Test
    @DisplayName("Deve alterar a senha do usuário com sucesso quando ele existir")
    void deveAlterarSenhaComSucesso() {
        when(usuarioOutputPort.buscarPorId(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioOutputPort.salvar(any(Usuario.class))).thenReturn(usuarioMock);

        assertDoesNotThrow(() -> usuarioUseCase.alterarSenha(1L, "novaSenha456"));

        assertEquals("novaSenha456", usuarioMock.getSenha());
        assertNotNull(usuarioMock.getDataUltimaAlteracao());
        verify(usuarioOutputPort, times(1)).buscarPorId(1L);
        verify(usuarioOutputPort, times(1)).salvar(usuarioMock);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar a senha de usuário que não existe")
    void deveLancarExcecaoAoAlterarSenhaDeUsuarioInexistente() {
        when(usuarioOutputPort.buscarPorId(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                usuarioUseCase.alterarSenha(99L, "novaSenha")
        );

        assertTrue(exception.getMessage().contains("Usuário não encontrado"));
        verify(usuarioOutputPort, never()).salvar(any(Usuario.class));
    }
}