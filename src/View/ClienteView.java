package View;

import Controller.*;
import Model.*;


import java.util.Scanner;

public class ClienteView {
    private Scanner sc = new Scanner(System.in);
    private Cliente cliente = new Cliente();
    private ClienteController clienteController;

    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente lerInformacoesCliente(){
        cliente = new Cliente();
        System.out.println("------------CADASTRAR CLIENTE------------------");
        System.out.println("Digite seu nome: ");
        cliente.setNome(EntradasController.formataNome(EntradasController.verificaBufferScaner(sc.nextLine())));
        System.out.println("Digite seu CPF: ");
        cliente.setCpf(sc.next());
        System.out.println("Digite seu email: ");
        cliente.setEmail( sc.next());
        System.out.println("Digite sua idade: ");
        cliente.setIdade(EntradasController.verificaQuantidade(sc.next()));
        System.out.println("Digite sua senha: -a senha deve no minimo ter 5 caracteres,sendo uma letra maiúscula e um caracter especial");
        cliente.setSenha(EntradasController.verificaSenha(sc.next()));
        return cliente;
    }
    public void cadastrarCliente(){
        cliente = lerInformacoesCliente();
        clienteController.cadastrarCliente(cliente);
    }

    public void listarClientes(){
        try {
            System.out.println("------------------LISTA DE CLIENTES------------------");
            for (Cliente cliente:clienteController.getCliente().getClientes()) {
                System.out.println(cliente.mostrar());
                System.out.println();
            }
        }catch (NullPointerException listaClientesvazio){
            System.out.println(listaClientesvazio.getMessage());
        }
    }
    public void buscarClientesPorCpf(){
        System.out.println("Digite o cpf do cliente desejado");
        String cpf = sc.next();
        EntradasController.validaCPF(cpf);
        clienteController.comprasPorCliente(cpf);
    }
}
