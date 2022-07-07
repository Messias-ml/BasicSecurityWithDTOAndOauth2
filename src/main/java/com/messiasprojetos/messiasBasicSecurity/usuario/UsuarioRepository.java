package com.messiasprojetos.messiasBasicSecurity.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByNome(String username);
}
