package Controller;

import Model.Cliente;
import Model.PessoaCadastrada;
import Repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    //O sistema não permite cadastrar clientes com CPFs repetidos;

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteController clienteController =new ClienteController();;

    @Test
    void cadastrarClienteComCpfEmailRepetido(){
        Cliente cliente = new Cliente("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(clienteRepository.clienteJaExiste(cliente)).thenReturn(true);
        Assertions.assertThrows(PessoaCadastrada.class,()->clienteController.cadastrarCliente(cliente));
        verify(clienteRepository,times(1)).clienteJaExiste(cliente);
        verify(clienteRepository,never()).adicionarCliente(cliente);
    }

    @Test
    void cadastrarClienteComCpfEmailNovo(){
        Cliente cliente = new Cliente("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(clienteRepository.clienteJaExiste(cliente)).thenReturn(false);
        clienteController.cadastrarCliente(cliente);
        verify(clienteRepository,times(1)).adicionarCliente(cliente);
    }

    @Test
    void procurarClienteComEmailESenhaDesconhecido(){
        Cliente cliente = new Cliente("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(clienteRepository.procuraClienteEmail(cliente.getEmail(),cliente.getSenha())).thenReturn(null);
        Assertions.assertNull(clienteController.verificaCredenciais(cliente.getEmail(), cliente.getSenha()));
    }
    @Test
    void procurarClienteComEmailESenhaConhecido(){
        Cliente cliente = new Cliente("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(clienteRepository.procuraClienteEmail(cliente.getEmail(),cliente.getSenha())).thenReturn(cliente);
        Assertions.assertEquals(cliente,clienteController.verificaCredenciais(cliente.getEmail(), cliente.getSenha()));
    }

    @Test
    void listarClienteComListaNaoVazia(){
        List<Cliente> clientes = new ArrayList<>();
        clientes.add( new Cliente("Ana","161.476.846-39","ana@alves.com",25,"Ju45!"));
        when(clienteRepository.getClientes()).thenReturn(clientes);
        clienteController.listarClientes();
        verify(clienteRepository,times(1)).getClientes();
    }

}
