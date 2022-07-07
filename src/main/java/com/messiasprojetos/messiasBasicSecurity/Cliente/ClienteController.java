package com.messiasprojetos.messiasBasicSecurity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClienteDTO> buscarClientes(){
        return service.buscarClientesDoBanco();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteDTO criarCliente(@RequestBody ClienteParaCriarDTO dadosDoCliente){
        return service.criarInserirClienteNoBanco(dadosDoCliente);
    }
}
