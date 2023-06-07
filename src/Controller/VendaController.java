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
            Venda venda1 = new Venda();
            venda1.setVendedor(vendedor);
            venda1.setCliente(cliente);
            venda1.setProduto(produto);
            estoque.retirarUnidadeProdutoEstoque(produto.getCodigoProduto(),quantidade);
            venda1.setQuantidade(quantidade);
            venda1.setTotalCompra(produto.getPreco()*quantidade);
            venda.adicionarVenda(venda1);
            System.out.println("--------------NOTA FISCAL------------------");
            System.out.println(venda1.mostrarVenda());
            System.out.println("--------------------------------------");
        }catch (NullPointerException | NumberFormatException erro){
            System.out.println(erro.getMessage());
        }
    }

    public Venda getVenda() {
        return venda;
    }
}
