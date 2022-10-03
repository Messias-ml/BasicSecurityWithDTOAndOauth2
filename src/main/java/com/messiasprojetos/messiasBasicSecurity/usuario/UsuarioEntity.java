package com.messiasprojetos.messiasBasicSecurity.usuario;

import com.messiasprojetos.messiasBasicSecurity.modelBDEntity.BdEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UsuarioEntity extends BdEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOM", nullable = false)
    private String nome;

    @Column(name = "SNH", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRF", nullable = false)
    private Perfi perfil = Perfi.USER;
}
