package com.messiasprojetos.messiasBasicSecurity.usuario;

import com.messiasprojetos.messiasBasicSecurity.usuario.dto.UsuarioDTO;
import com.messiasprojetos.messiasBasicSecurity.usuario.dto.UsuarioPostDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = repository.findByNome(username);

        return User.builder()
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .roles(usuario.getPerfil().getRole())
                .build();
    }

    public UsuarioDTO criarUsuario(UsuarioPostDTO usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setSenha(encoder.encode(usuario.getSenha()));

        repository.save(usuarioEntity);

        return transformandoEmUsuarioDTO(usuarioEntity);
    }

    private UsuarioDTO transformandoEmUsuarioDTO(UsuarioEntity usuarioEntity) {
        return new UsuarioDTO(usuarioEntity.getNome());
    }

    public List<UsuarioDTO> buscarTodosUsuarios(){
        return repository.findAll()
                .stream()
                .map(u -> new UsuarioDTO(u.getNome())).collect(Collectors.toList());
    }
}
