package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import Controller.*;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private String codigo;
    private Produto produto;
    private String nomeProduto;
    private double preco;
    private int quantidade;
    private double totalCompra;
    private String  dataCompra;

    private List<Venda> vendas = new ArrayList<>();

    public Venda() {
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataCompra = data.format(formatarData);

    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {

        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<Venda> getVendas() {
        if (vendas.isEmpty()) throw new NullPointerException("Lista de vendas vazia");
        return vendas;
    }

    public void adicionarVenda(Venda venda){
        vendas.add(venda);
    }
    public String mostrarVenda(){
        return "Model.Vendedor: "+vendedor.getNome()+"\nModel.Cliente: "+cliente.getNome()+"\nData da compra: "+dataCompra
                +"\nCódigo produto: "+produto.getCodigoProduto()+"\nNome do Model.Produto: "+produto.getNome()+"\nPreço unitário: "+ FormataValores.formataPreco(produto.getPreco())+
                "\nQuantidade: "+quantidade+"\nValor total da compra: "+ FormataValores.formataPreco(totalCompra);

    }
}
