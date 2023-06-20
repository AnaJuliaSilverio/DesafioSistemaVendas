package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Controller.*;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private double totalCompra;
    private String dataCompra;

    public Venda() {
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataCompra = data.format(formatarData);
    }

    public Venda(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade, double totalCompra) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.totalCompra = totalCompra;
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataCompra = data.format(formatarData);
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
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }


    public String mostrarVenda(){
        return "Vendedor: "+vendedor.getNome()+"\nCliente: "+cliente.getNome()+"\nData da compra: "+dataCompra
                +"\nCódigo produto: "+produto.getCodigoProduto()+"\nNome do Produto: "+produto.getNome()+"\nPreço unitário: "+ EntradasController.formataPreco(produto.getPreco())+
                "\nQuantidade: "+quantidade+"\nValor total da compra: "+ EntradasController.formataPreco(totalCompra);
    }
}
