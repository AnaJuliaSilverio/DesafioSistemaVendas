package Model;

import Controller.EntradasController;

import java.util.Objects;

public class Produto {
    private String nome;
    private double preco;
    static int contCodigoProduto = 0;
    private int codigoProduto;
    private String descricao;
    private String categoria;

    public Produto(String nome, double preco, String descricao, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.categoria = categoria;
        Produto.contCodigoProduto++;
        this.codigoProduto = contCodigoProduto;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String mostrarProduto(){
        return "Código: "+codigoProduto+"\nNome: "+nome+"\nPreço: "+ EntradasController.formataPreco(preco) +"\nCategoria: "+categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.preco, preco) == 0 && Objects.equals(nome, produto.nome) && Objects.equals(categoria, produto.categoria);
    }

}
