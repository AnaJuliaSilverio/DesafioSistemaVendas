package Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto,Integer> produtosDisponiveis = new LinkedHashMap<>();
    private Map<Produto,Integer> produtosComDesconto = new LinkedHashMap<>();

    public Map<Produto, Integer> getProdutosComDesconto() {
        produtosComDesconto.putAll(produtosDisponiveis);
        return produtosComDesconto;
    }

    public Estoque() {

        iniciarEstoque();
    }

    public void adicionarProdutoEstoque(int quantidade, Produto produto){
        String quantidadeString = Integer.toString(quantidade);
        if (!quantidadeString.matches("[0-9]+")){
            throw new NumberFormatException("A quantidade deve conter apenas numeros");
        }
        for (Produto p:produtosDisponiveis.keySet()) {
            if (p.equals(produto)) {
                throw new IllegalArgumentException("Model.Produto já existe!");
            }
        }
      produtosDisponiveis.put(produto,quantidade);
    }
    public void atualizarProdutoEstoque(int quantidade, int codigo){
        String quantidadeString = Integer.toString(quantidade);
        if (!quantidadeString.matches("[0-9]+")){
            throw new NumberFormatException("A quantidade deve conter apenas numeros");
        }
        for (Produto produto:produtosDisponiveis.keySet()) {
            if (produto.getCodigoProduto()==codigo){
                int novaQuantidade = produtosDisponiveis.get(produto)+quantidade;
                produtosDisponiveis.replace(produto,novaQuantidade);
                return;
            }
            }
        throw new NullPointerException("Esse código não está relacionado com nenhum produto");
        }

    public void retirarUnidadeProdutoEstoque(int codigo,int quantidade){
        for (Produto produto:produtosDisponiveis.keySet()) {
            if (produto.getCodigoProduto()==codigo){
                if (produtosDisponiveis.get(produto)>quantidade){
                    int novaQuantidade = produtosDisponiveis.get(produto)-quantidade;
                    produtosDisponiveis.replace(produto,novaQuantidade);
                } else if (produtosDisponiveis.get(produto) == quantidade) {
                    removerProdutoEstoque(codigo);
                }else throw new IllegalArgumentException("Essa quantidade está indisponível");
                return;
            }
        }
        throw new NullPointerException("Esse código não está relacionado com nenhum produto");
    }
    public boolean removerProdutoEstoque(int codigo){
        for (Produto produto:produtosDisponiveis.keySet()) {
            if (produto.getCodigoProduto()==codigo){
                produtosDisponiveis.remove(produto);
                return true;
            }
        }
        throw new NullPointerException("Model.Produto não está no estoque");
    }


    public Map<Produto,Integer> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }
    private void iniciarEstoque(){
        produtosDisponiveis.put(new Produto("iPhone 12", 4999.0, "Smartphone top de linha", "Eletrônicos"),10);
        produtosDisponiveis.put(new Produto("Nike Air Zoom Pegasus 37", 499.9, "Tênis esportivo", "Calçados"),5);
        produtosDisponiveis.put(new Produto("Livro - A Menina que Roubava Livros", 39.9, "Best-seller de Markus Zusak", "Livros"),20);
        produtosDisponiveis.put(new Produto("Smart TV Samsung 55", 3499.99, "Televisão 4K com tela grande", "Eletrônicos"),3);
        produtosDisponiveis.put(new Produto("Camiseta Polo Lacoste", 299.9, "Camiseta de algodão com logo bordado", "Vestuário"),15);
        produtosDisponiveis.put(new Produto("Fone de Ouvido Bluetooth JBL", 249.9, "Fones de ouvido sem fio com cancelamento de ruído", "Eletrônicos"),12);
        produtosDisponiveis.put(new Produto("Mochila Escolar Kipling", 199.9, "Mochila resistente e durável para estudantes", "Acessórios"),12);
        produtosDisponiveis.put(new Produto("Relógio Casio G-Shock", 599.9, "Relógio resistente a choques e água", "Acessórios"),7);
        produtosDisponiveis.put(new Produto("Câmera DSLR Canon EOS Rebel T7", 2699.0, "Câmera profissional com lente inclusa", "Eletrônicos"),4);
        produtosDisponiveis.put(new Produto("Console de Videogame PlayStation 5", 4499.0, "Console de última geração com gráficos de alta qualidade", "Eletrônicos"),2);
    }






}
