package Controller;

import Model.Cliente;
import Model.Produto;
import Model.Venda;
import Model.Vendedor;
import Repository.VendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class VendaControllerTest {
    @Mock
    EstoqueController estoqueController;
    @Mock
    VendaRepository vendaRepository;
    @InjectMocks
    VendaController vendaController =new VendaController(estoqueController);

    @Test
    void listarVendasListaVazia(){
            List<Venda> vendas = new ArrayList<>();
            vendas.add(new Venda());
            when(vendaRepository.getVendas()).thenReturn(vendas);
        Assertions.assertThrows(NullPointerException.class,()->vendaController.vendas());
    }
    @Test
    void listarVendasListaNaoVazia(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        Cliente cliente = new Cliente("Julia","161.476.846-39","ana@alves.com",25,"Ju45!");
        Produto produto = new Produto("iPhone 12", 4999.0, "Smartphone top de linha", "Eletrônicos");
        int quantidade =5;
        double valor = 68.70;
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda(vendedor,cliente,produto,quantidade,valor));
        when(vendaRepository.getVendas()).thenReturn(vendas);
        vendaController.vendas();
    }
    @Test
    void listarVendaPorCliente(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        Cliente cliente = new Cliente("Julia","161.476.846-39","ana@alves.com",25,"Ju45!");
        Produto produto = new Produto("iPhone 12", 4999.0, "Smartphone top de linha", "Eletrônicos");
        int quantidade =5;
        double valor = 68.70;
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda(vendedor,cliente,produto,quantidade,valor));
        when(vendaRepository.getVendas()).thenReturn(vendas);
        vendaController.vendasPorCliente("161.476.846-39");
    }

    @Test
    void listarVendaPorVendedor(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        Cliente cliente = new Cliente("Julia","161.476.846-39","ana@alves.com",25,"Ju45!");
        Produto produto = new Produto("iPhone 12", 4999.0, "Smartphone top de linha", "Eletrônicos");
        int quantidade =5;
        double valor = 68.70;
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda(vendedor,cliente,produto,quantidade,valor));
        when(vendaRepository.getVendas()).thenReturn(vendas);
        vendaController.vendasPorVendedor("ana@alves.com");
    }

    @Test
    void vendaComSucesso(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        Cliente cliente = new Cliente("Julia","161.476.846-39","ana@alves.com",25,"Ju45!");
        Produto produto = new Produto("iPhone 12", 4999.0, "Smartphone top de linha", "Eletrônicos");
        int quantidade =5;
        vendaController.vender(vendedor,cliente,produto,quantidade);
        verify(estoqueController, times(1)).retirarUnidadeProdutoEstoque(produto.getCodigoProduto(), 5);
        verify(vendaRepository, times(1)).adicionarVenda(any(Venda.class));
    }

    @Test
        void vendaSemSucesso(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        Cliente cliente = new Cliente("Julia","161.476.846-39","ana@alves.com",25,"Ju45!");
        Produto produto = new Produto("iPhone 12", 4999.0, "Smartphone top de linha", "Eletrônicos");
        int quantidade =5;
        doThrow(new IllegalArgumentException("Essa quantidade está indisponíve")).when(estoqueController).retirarUnidadeProdutoEstoque(produto.getCodigoProduto(),5);
        Assertions.assertThrows(IllegalArgumentException.class,()->vendaController.vender(vendedor,cliente,produto,quantidade));
        verify(estoqueController, times(1)).retirarUnidadeProdutoEstoque(produto.getCodigoProduto(), 5);
        verify(vendaRepository, never()).adicionarVenda(any(Venda.class));
    }
}