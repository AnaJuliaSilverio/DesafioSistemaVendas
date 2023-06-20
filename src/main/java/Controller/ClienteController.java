package Controller;

import Model.Cliente;
import Model.PessoaCadastrada;
import Model.Venda;
import Repository.ClienteRepository;
import View.IniciarPrograma;

public class ClienteController {
    private ClienteRepository clienteRepository = new ClienteRepository();

    private IniciarPrograma iniciarPrograma =new IniciarPrograma(clienteRepository);;

    public ClienteController() {
        iniciarPrograma.iniciarBDCliente();
    }


    public void cadastrarCliente(Cliente cliente){
        if (clienteRepository.clienteJaExiste(cliente)){throw new PessoaCadastrada("Cliente já cadastrado");}
        clienteRepository.adicionarCliente(cliente);
    }
    public void listarClientes(){
            System.out.println("------------------LISTA DE CLIENTES------------------");
            for (Cliente cliente:this.clienteRepository.getClientes()) {
                System.out.println(cliente.mostrar());
                System.out.println();
            }

    }

    public Cliente verificaCredenciais(String email,String senha){
        return clienteRepository.procuraClienteEmail(email,senha);
    }
}
