package com.messiasprojetos.messiasBasicSecurity.Cliente;

import lombok.Data;

@Data
public class ClienteParaCriarDTO {

    private String nome;

    private String cpf;

    private Long numeroTelefone;

    private Integer idade;
}
