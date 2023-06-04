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

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String mostrarProduto(){
        return "Código: "+codigoProduto+"\nNome: "+nome+"\nPreço: R$"+preco+"\nCategoria: "+categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.preco, preco) == 0 && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(categoria, produto.categoria);
    }

}
