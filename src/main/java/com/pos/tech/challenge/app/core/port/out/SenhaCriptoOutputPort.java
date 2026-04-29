package com.pos.tech.challenge.app.core.port.out;

public interface SenhaCriptoOutputPort {
    String criptografar(String senha);
    boolean matches(String senhaEmTexto, String senhaCriptografada);
}
