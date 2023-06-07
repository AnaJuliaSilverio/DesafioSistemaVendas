package Controller;

import Model.Estoque;
import Model.Produto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EstoqueController {
    private Estoque estoque = new Estoque();
    private Produto produto;
    private boolean existeDesconto;
    private LocalDate dataAlteracao;


    public void cadastrarNovoProduto(String nome, double preco, String descricao, String categoria, int quantidade){
        try {
            produto = new Produto(nome,preco,descricao,categoria);
            estoque.adicionarProdutoEstoque(quantidade,produto);
        }catch (IllegalArgumentException erro){
            System.out.println(erro.getMessage());
        }

    }
    public void atualizaQtdProduto(int quantidade,int codigo){
        try {
            estoque.atualizarProdutoEstoque(quantidade,codigo);
        }catch (NullPointerException erro){
            System.out.println(erro.getMessage());
        }
    }
    public Produto consultarProduto(int codigo) {
        for (Produto produto : estoque.getProdutosDisponiveis().keySet()) {
            if (produto.getCodigoProduto() == codigo) {
                return produto;
            }
        }
        throw new NullPointerException("Produto não está no estoque");
    }
    public void retirarUnidadeProdutoEstoque(int codigo,int quantidade){
        for (Produto produto:estoque.getProdutosDisponiveis().keySet()) {
            if (produto.getCodigoProduto()==codigo){
                if (estoque.getProdutosDisponiveis().get(produto)>quantidade){
                    int novaQuantidade = estoque.getProdutosDisponiveis().get(produto)-quantidade;
                    estoque.getProdutosDisponiveis().replace(produto,novaQuantidade);
                } else if (estoque.getProdutosDisponiveis().get(produto) == quantidade) {
                    removerProdutoEstoque(codigo);
                }else throw new IllegalArgumentException("Essa quantidade está indisponível");
                return;
            }
        }
        throw new NullPointerException("Esse código não está relacionado com nenhum produto");
    }
    private boolean removerProdutoEstoque(int codigo){
        for (Produto produto:estoque.getProdutosDisponiveis().keySet()) {
            if (produto.getCodigoProduto()==codigo){
                estoque.getProdutosDisponiveis().remove(produto);
                return true;
            }
        }
        throw new NullPointerException("Model.Produto não está no estoque");
    }
    public void exibirEstoque(){
        if (verificaDia()){
            for (Produto key:estoque.getProdutosComDesconto().keySet()) {
                System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoque.getProdutosComDesconto().get(key));
                System.out.println();
            }
        }else {
            for (Produto key:estoque.getProdutosDisponiveis().keySet()) {
                System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoque.getProdutosDisponiveis().get(key));
                System.out.println();
            }
        }
    }
    public void listarProdutos(){
        if (verificaDia()){
            for (Produto produto:estoque.getProdutosComDesconto().keySet()) {
                System.out.println(produto.mostrarProduto());
                System.out.println();
            }
        }else {
            for (Produto produto:estoque.getProdutosDisponiveis().keySet()) {
                System.out.println(produto.mostrarProduto());
                System.out.println();
            }
        }
    }

    public void adicionarDesconto(double desconto,String data){
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAlteracao = LocalDate.parse(data,formatarData);
        if (desconto>=100) throw new IllegalArgumentException("A porcentagem deve estar entre 1 e 100");
        this.existeDesconto = true;
        desconto = 1-(desconto/100);
        for (Produto produto:estoque.getProdutosComDesconto().keySet()) {
            produto.setPreco(produto.getPreco()*desconto);
        }
    }
    public boolean verificaDia(){
        if (this.existeDesconto && dataAlteracao.isAfter(LocalDate.now())) return true;
        else {
            this.existeDesconto = false;
            return false;
        }
    }
}
