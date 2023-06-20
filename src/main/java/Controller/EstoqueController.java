package Controller;

import Model.Produto;
import Repository.EstoqueRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EstoqueController {
    private EstoqueRepository estoqueRepository = new EstoqueRepository();
    private Produto produto;
    private boolean existeDesconto;
    private LocalDate dataAlteracao;


    public void cadastrarNovoProduto(String nome, double preco, String descricao, String categoria, int quantidade){
        produto = new Produto(nome,preco,descricao,categoria);
        if (estoqueRepository.produtoExiste(produto)!=null) throw new IllegalArgumentException("Esse produto já existe");
        estoqueRepository.adicionarProduto(quantidade,produto);
    }
    public void atualizaQtdProduto(int quantidade,int codigo){
        estoqueRepository.atualizarProdutoEstoque(quantidade,codigo);
    }
    public Produto consultarProduto(int codigo) {
        Produto produto = estoqueRepository.produtoExiste(codigo);
        if (produto == null) throw new IllegalArgumentException("Produto nao existe");
        return produto;
    }

    public void retirarUnidadeProdutoEstoque(int codigo,int quantidade){
        estoqueRepository.retirarUnidadeDoProduto(codigo,quantidade);

    }

    public void exibirEstoque(){
        if (verificaDia()){
            for (Produto key:estoqueRepository.getProdutosComDesconto().keySet()) {
                System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoqueRepository.getProdutosComDesconto().get(key));
                System.out.println();
            }
        }else {
            for (Produto key:estoqueRepository.getProdutosDisponiveis().keySet()) {
                System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoqueRepository.getProdutosDisponiveis().get(key));
                System.out.println();
            }
        }
    }
    public void listarProdutos(){
        if (verificaDia()){
            for (Produto produto:estoqueRepository.getProdutosComDesconto().keySet()) {
                System.out.println(produto.mostrarProduto());
                System.out.println();
            }
        }else {
            for (Produto produto:estoqueRepository.getProdutosDisponiveis().keySet()) {
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
        estoqueRepository.adicionarDesconto(desconto);
    }
    public boolean verificaDia(){
        if (this.existeDesconto && dataAlteracao.isAfter(LocalDate.now())) return true;
        else {
            this.existeDesconto = false;
            return false;
        }
    }


}
