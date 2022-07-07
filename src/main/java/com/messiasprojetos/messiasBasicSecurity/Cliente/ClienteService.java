package com.messiasprojetos.messiasBasicSecurity.Cliente;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteService {

    private BDClientesRepository repository;

    /************************METODOS QUE SERÃO CHAMADOS***************************/

    public List<ClienteDTO> buscarClientesDoBanco() {
        List<ClienteEntity> listaDeClientesDoBD = repository.findAll();
        return transformaClienteEntityParaDTO(listaDeClientesDoBD);
    }

    public ClienteDTO criarInserirClienteNoBanco(ClienteParaCriarDTO dadosDoCliente) {
        ClienteEntity clienteEntity = transformandoDadosDoClienteParaSerSalvoNoBancoDeDados(dadosDoCliente);
        clienteEntity = repository.save(clienteEntity);
        return transformandoClienteDoBancoEmDTO_ParaMostrarUsuario(clienteEntity);

    }

    /************************METODOS DE CONVERSÃO DE CLASSE***************************/

    private List<ClienteDTO> transformaClienteEntityParaDTO(List<ClienteEntity> listaDeClientesDoBD) {
        return listaDeClientesDoBD
                .stream()
                .map(this::transformandoClienteDoBancoEmDTO_ParaMostrarUsuario)
                .collect(Collectors.toList());
    }

    private ClienteDTO transformandoClienteDoBancoEmDTO_ParaMostrarUsuario(ClienteEntity clienteEntity) {
        ClienteDTO clienteParaMostrarProUsuario = new ClienteDTO();
        clienteParaMostrarProUsuario.setCpf(clienteEntity.getCpf());
        clienteParaMostrarProUsuario.setIdade(clienteEntity.getIdade());
        clienteParaMostrarProUsuario.setNome(clienteEntity.getNome());
        return clienteParaMostrarProUsuario;
    }

    private ClienteEntity transformandoDadosDoClienteParaSerSalvoNoBancoDeDados(ClienteParaCriarDTO dadosDoCliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(dadosDoCliente.getCpf());
        clienteEntity.setNome(dadosDoCliente.getNome());
        clienteEntity.setIdade(dadosDoCliente.getIdade());
        clienteEntity.setNumeroTelefone(dadosDoCliente.getNumeroTelefone());
        return clienteEntity;
    }
}
