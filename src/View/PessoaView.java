package View;

import Controller.ClienteController;
import Controller.EntradasController;
import Controller.VendaController;
import Controller.VendedorController;
import Model.Pessoa;

import java.util.Scanner;

public class PessoaView {
    ClienteController clienteController;
    VendedorController vendedorController;
    VendedorView vendedorView;
    ClienteView clienteView;
    private Scanner sc = new Scanner(System.in);

    public PessoaView(ClienteController clienteController, VendedorController vendedorController, VendedorView vendedorView, ClienteView clienteView) {
        this.clienteController = clienteController;
        this.vendedorController = vendedorController;
        this.vendedorView = vendedorView;
        this.clienteView = clienteView;
    }

    public String login(){
        System.out.println("Digite seu email: ");
        String email = sc.next();
        EntradasController.verificaEmail(email);
        System.out.println("Digite sua senha: ");
        String senha = sc.next();
        if (clienteController.verificaCredenciais(email,senha)!= null) {
            clienteView.setCliente(clienteController.verificaCredenciais(email, senha));
            return "cliente";
        }
        else if (vendedorController.verificaCredenciais(email,senha)!=null){
            vendedorView.setVendedor(vendedorController.verificaCredenciais(email,senha));
            return "vendedor";
        }
        else throw new NullPointerException("Pessoa não cadastrada!");
    }

}

