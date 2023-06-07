package View;

import Controller.*;
import Model.*;

import java.util.Scanner;

public class VendaView {
    private Scanner sc = new Scanner(System.in);
    private VendaController vendaController;
    EstoqueController estoqueController;

    public VendaView(VendaController vendaController,EstoqueController estoqueController) {
        this.vendaController = vendaController;
        this.estoqueController = estoqueController;
    }
    public void realizarVenda(Vendedor vendedor,Cliente cliente){
        Produto produto;
        int quantidade;
        System.out.println("Digite o código do produto: ");
        produto = estoqueController.consultarProduto(EntradasController.verificaQuantidade(sc.next()));
        System.out.println("Quantas unidade de " + produto.getNome()+ " deseja comprar?");
        quantidade = EntradasController.verificaQuantidade(sc.next());
        sc.nextLine();
        vendaController.vender(vendedor, cliente, produto, quantidade);
    }
    public void listarVendas(){
        try {
            System.out.println("------------------LISTA DE VENDAS------------------");
            for (Venda venda: vendaController.getVenda().getVendas()) {
                System.out.println(venda.mostrarVenda());
                System.out.println();
            }
        }catch ( NullPointerException listaVendaVazia){
            System.out.println(listaVendaVazia.getMessage());
        }
    }
}
