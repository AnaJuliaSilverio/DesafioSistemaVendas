package View;

import Controller.*;
import Model.*;

import java.util.Scanner;

public class EstoqueView {
    private Scanner sc = new Scanner(System.in);
    private Venda venda = new Venda();
    private EstoqueController estoqueController;

    public EstoqueView(EstoqueController estoqueController) {
        this.estoqueController = estoqueController;
    }

    public void cadastrarProduto(){
        System.out.println("------------CADASTRAR PRODUTO------------------");
        System.out.println("Digite o nome do produto: ");
        String nome = EntradasController.formataNome(EntradasController.verificaBufferScaner(sc.nextLine()));
        System.out.println("Digite a descricao: ");
        String descricao = EntradasController.verificaBufferScaner(sc.nextLine());
        System.out.println("Digite a categoria: ");
        String categoria = EntradasController.verificaBufferScaner(sc.nextLine());
        System.out.println("Digite a quantidade: ");
        int quantidade = EntradasController.verificaQuantidade(sc.next());
        System.out.println("Digite o preço: ");
        double preco = EntradasController.verificaPreco(sc.next());
        estoqueController.cadastrarNovoProduto(nome,preco,descricao,categoria,quantidade);
    }
    public void atualizarProduto(){
        System.out.println("Digite o código do produto: ");
        int codigo = EntradasController.verificaQuantidade(sc.next());
        System.out.println("Digite a quantidade a ser adicionada ao estoque: ");
        int quantidade = EntradasController.verificaQuantidade(sc.next());
        estoqueController.atualizaQtdProduto(quantidade, codigo);
    }
    public void aplicarDesconto(){
        System.out.println("Digite a porcentagem do desconto: ");
        double desconto = EntradasController.verificaPreco(sc.next());
        System.out.println("Digite até que dia esse desconto será valido: (Formato esperado: dd/mm/yyyy)");
        String data =sc.next();
        estoqueController.adicionarDesconto(desconto,data);
    }
    public void exibirProduto(){
        estoqueController.listarProdutos();
    }
    public void exibirEstoque(){
        estoqueController.exibirEstoque();
    }

}
