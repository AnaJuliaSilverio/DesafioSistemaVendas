package View;

import Controller.*;
import Model.*;

import java.util.Scanner;

public class VendedorView {
    private Scanner sc = new Scanner(System.in);
    private Vendedor vendedor;
    private VendedorController vendedorController;

    public VendedorView(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    public Vendedor getVendedor() {
        return vendedor;
    }
    public void setVendedor() {
        this.vendedor= vendedorController.turnoDaVez();
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor= vendedor;
    }
    public VendedorView(VendedorController vendedorController) {
        this.vendedorController = vendedorController;
    }
    public void listarVendedores(){
        try {
            System.out.println("------------------LISTA DE VENDEDORES------------------");
            for (Vendedor vendedor: vendedorController.getVendedor().getVendedores()){
                System.out.println(vendedor.mostrar());
                System.out.println();
            }
        }catch ( NullPointerException listaVendedoresVazia){
            System.out.println(listaVendedoresVazia.getMessage());
        }
    }
    public void buscarVendedorPorEmail(){
        System.out.println("Digite o email do vendedor desejado");
        String email = sc.next();
        EntradasController.verificaEmail(email);
        vendedorController.comprasPorVendedor(email);
    }
    public void cadastrarVendedor(){
        vendedorController.cadastrarVendedor(lerInformacoesVendedor());
    }
    public Vendedor lerInformacoesVendedor(){
        vendedor = new Vendedor();
        System.out.println("------------CADASTRAR VENDEDOR------------------");
        System.out.println("Digite seu nome: ");
        vendedor.setNome(EntradasController.formataNome(EntradasController.verificaBufferScaner(sc.nextLine())));
        System.out.println("Digite seu CPF: ");
        vendedor.setCpf(sc.next());
        System.out.println("Digite seu email: ");
        vendedor.setEmail( sc.next());
        System.out.println("Digite sua idade: ");
        vendedor.setIdade( EntradasController.verificaQuantidade(sc.next()));
        return vendedor;
    }

}
