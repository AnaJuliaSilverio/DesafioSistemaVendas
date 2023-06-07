package Controller;

import Model.*;

import java.util.List;

public class VendaController {
    private Venda venda = new Venda();
    private EstoqueController estoque;

    public VendaController(EstoqueController estoque) {
        this.estoque = estoque;
    }

    public void vender(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade){
        try {
            venda = new Venda();
            venda.setVendedor(vendedor);
            venda.setCliente(cliente);
            venda.setProduto(produto);
            estoque.retirarUnidadeProdutoEstoque(produto.getCodigoProduto(),quantidade);
            venda.setQuantidade(quantidade);
            venda.setTotalCompra(produto.getPreco()*quantidade);
            venda.adicionarVenda(venda);
            System.out.println("--------------NOTA FISCAL------------------");
            System.out.println(venda.mostrarVenda());
            System.out.println("--------------------------------------");
        }catch (NullPointerException | NumberFormatException erro){
            System.out.println(erro.getMessage());
        }
    }

    public Venda getVenda() {
        return venda;
    }
}
