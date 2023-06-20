package Controller;

import Model.Produto;
import Repository.EstoqueRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class EstoqueControllerTest {
    @Mock
    private EstoqueRepository estoqueRepository;
    @InjectMocks
    private EstoqueController estoqueController = new EstoqueController();
    @Test
    public void testCadastrarNovoProdutoQueNaoExiste() {
        String nome = "iPhone 12";
        double preco = 4999.0;
        String descricao = "Smartphone top de linha";
        String categoria = "Eletrônicos";
        int quantidade = 10;

        Produto produto = new Produto(nome, preco, descricao, categoria);

        when(estoqueRepository.produtoExiste(produto)).thenReturn(null);

        estoqueController.cadastrarNovoProduto(nome, preco, descricao, categoria, quantidade);

        verify(estoqueRepository,times(1)).adicionarProduto(quantidade, produto);
    }

    @Test
    public void testCadastrarNovoProdutoComProdutoExistente() {
        String nome = "iPhone 12";
        double preco = 4999.0;
        String descricao = "Smartphone top de linha";
        String categoria = "Eletrônicos";
        int quantidade = 10;
        Produto produto = new Produto(nome, preco, descricao, categoria);

        when(estoqueRepository.produtoExiste(produto)).thenReturn(produto);

        assertThrows(IllegalArgumentException.class, () -> estoqueController.cadastrarNovoProduto(nome, preco, descricao, categoria, quantidade));

        verify(estoqueRepository, never()).adicionarProduto(quantidade, produto);
    }
    @Test
    void consultarProdutoComCodInexistente(){
        String nome = "iPhone 12";
        double preco = 4999.0;
        String descricao = "Smartphone top de linha";
        String categoria = "Eletrônicos";
        Produto produto = new Produto(nome, preco, descricao, categoria);
        when(estoqueRepository.produtoExiste(produto.getCodigoProduto())).thenReturn(null);
        assertThrows(IllegalArgumentException.class,()->estoqueController.consultarProduto(produto.getCodigoProduto()));
    }

    @Test
    void consultarProdutoComCodExistente(){
        String nome = "iPhone 12";
        double preco = 4999.0;
        String descricao = "Smartphone top de linha";
        String categoria = "Eletrônicos";
        Produto produto = new Produto(nome, preco, descricao, categoria);
        when(estoqueRepository.produtoExiste(produto.getCodigoProduto())).thenReturn(produto);
        assertEquals(produto,estoqueController.consultarProduto(produto.getCodigoProduto()));

    }
    @Test
    void adicionarDescontoInvalido(){
        assertThrows(IllegalArgumentException.class,()->estoqueController.adicionarDesconto(105,"10/05/2023"));
        verify(estoqueRepository,never()).adicionarDesconto(105);

    }
    @Test
    void adicionarDescontoValido(){
        estoqueController.adicionarDesconto(5,"10/05/2023");
        verify(estoqueRepository,times(1)).adicionarDesconto(5);
    }

    @Test
    void verificarDiaSemDesconto(){
        boolean resultado = estoqueController.verificaDia();
        assertFalse(resultado);
    }
    @Test
    void verificarDiaComDesconto(){
        estoqueController.adicionarDesconto(10,"26/12/2023");
        boolean resultado = estoqueController.verificaDia();
        assertTrue(resultado);
    }
    @Test
    void atualizandoProdutoComCodigoInvalido(){
        int quantidade = 5;
        int codigo = 123;
        doThrow(new NullPointerException("Esse código não está relacionado com nenhum produto"))
                .when(estoqueRepository).atualizarProdutoEstoque(quantidade, codigo);

        assertThrows(NullPointerException.class,()->estoqueController.atualizaQtdProduto(quantidade, codigo));
    }
    @Test
    void atualizandoProdutoComCodigoValido(){
       assertDoesNotThrow(()->estoqueController.atualizaQtdProduto(5, 1));
       verify(estoqueRepository).atualizarProdutoEstoque(5,1);
    }
    @Test
    void retirarProdutoComQuantidadeIndisponivel(){
        int quantidade = 5;
        int codigo = 123;
        doThrow(new IllegalArgumentException("Essa quantidade está indisponíve")).when(estoqueRepository).retirarUnidadeDoProduto(codigo,quantidade);
        assertThrows(IllegalArgumentException.class,()->estoqueController.retirarUnidadeProdutoEstoque(codigo,quantidade));
    }
    @Test
    void retirarProdutoComQuantidadeDisponivel(){
        assertDoesNotThrow(()->estoqueController.retirarUnidadeProdutoEstoque(5, 1));
        verify(estoqueRepository).retirarUnidadeDoProduto(5,1);
    }

    @Test
    void exibirEstoque(){
        Map<Produto,Integer> produtosDisponiveis = new LinkedHashMap<>();
        when(estoqueRepository.getProdutosDisponiveis()).thenReturn(produtosDisponiveis);
        produtosDisponiveis.put(new Produto("Produto 1", 10.0, "Descrição", "Categoria"), 5);
        estoqueController.exibirEstoque();
    }
    @Test
    void listarProdutos(){
        Map<Produto,Integer> produtosDisponiveis = new LinkedHashMap<>();
        when(estoqueRepository.getProdutosDisponiveis()).thenReturn(produtosDisponiveis);
        produtosDisponiveis.put(new Produto("Produto 1", 10.0, "Descrição", "Categoria"), 5);
        estoqueController.exibirEstoque();
    }



}
