package Controller;

import Model.*;
import Repository.VendaRepository;


public class VendaController {
    private VendaRepository vendaRepository = new VendaRepository();
    private EstoqueController estoque;

    public VendaController(EstoqueController estoque) {
        this.estoque = estoque;
    }

    public void vender(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade){
            Venda venda = new Venda();
            venda.setVendedor(vendedor);
            venda.setCliente(cliente);
            venda.setProduto(produto);
            estoque.retirarUnidadeProdutoEstoque(produto.getCodigoProduto(),quantidade);
            venda.setQuantidade(quantidade);
            venda.setTotalCompra(produto.getPreco()*quantidade);
            vendaRepository.adicionarVenda(venda);
            System.out.println("--------------NOTA FISCAL------------------");
            System.out.println(venda.mostrarVenda());
            System.out.println("--------------------------------------");

    }
    public void vendasPorCliente(String cpf){
        for (Venda venda: vendaRepository.getVendas()) {
            if (venda.getCliente().getCpf().equals(cpf)){
                System.out.println(venda.mostrarVenda());
                System.out.println();
            }
        }
    }
    public void vendasPorVendedor(String email){
        for (Venda venda: vendaRepository.getVendas()) {
            if (venda.getVendedor().getEmail().equals(email)){
                System.out.println( venda.mostrarVenda());
                System.out.println();
                }
        }
    }
    public void vendas(){
        System.out.println("------------------LISTA DE VENDAS------------------");
        for (Venda venda: vendaRepository.getVendas()) {
            System.out.println(venda.mostrarVenda());
            System.out.println();
        }
    }

}
