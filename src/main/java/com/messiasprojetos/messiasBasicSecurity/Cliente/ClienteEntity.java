package com.messiasprojetos.messiasBasicSecurity.Cliente;

import com.messiasprojetos.messiasBasicSecurity.modelBDEntity.BdEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ClienteEntity extends BdEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOM", nullable = false)
    private String nome;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "NUM_TEL", nullable = false)
    private Long numeroTelefone;

    @Column(name = "IDE", nullable = false)
    private Integer idade;
}
