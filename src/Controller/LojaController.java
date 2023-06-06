package Controller;

import Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import View.*;
public class LojaController {
    private Cliente clientesController=new Cliente();
    private Scanner sc = new Scanner(System.in);
    private Vendedor vendedorController=new Vendedor();
    private Produto produto;
    private Estoque estoque = new Estoque();
    private Venda vendasController = new Venda();
    private boolean existeDesconto;
    private LocalDate dataAlteracao;

    public LojaController() {
        IniciarPrograma iniciarPrograma= new IniciarPrograma(vendedorController,clientesController);
    }

    public int verificaOpcaoMenu(String opcao, HashMap<String,String> menu) throws IllegalArgumentException{
        for (String op:menu.keySet()) {
            if (op.equals(opcao)||menu.get(op).equals(opcao)) return Integer.parseInt(op);
        }
        throw new IllegalArgumentException("Por favor digite uma opção válida");
    }
    public void cadastrarCliente(Cliente cliente){
            clientesController.adicionarCliente(cliente);
    }

    public void cadastrarVendedor(Vendedor vendedor){
        vendedorController.adicionarVendedor(vendedor);
    }

    public Pessoa verificaCredencial(String email) throws NullPointerException{
        if (clientesController.procuraClienteEmail(email)!=null) return clientesController.procuraClienteEmail(email);
        else if (vendedorController.procuraVendedorEmail(email)!=null) return vendedorController.procuraVendedorEmail(email);
        else throw new NullPointerException("Pessoa não cadastrada!");
    }

    public void cadastrarNovoProduto(String nome, double preco, String descricao, String categoria,int quantidade){
        try {
            produto = new Produto(nome,preco,descricao,categoria);
            estoque.adicionarProdutoEstoque(quantidade,produto);
        }catch (IllegalArgumentException erro){
            System.out.println(erro.getMessage());
        }

    }
    public void atualizaQtdProduto(int quantidade,int codigo){
        try {
            estoque.atualizarProdutoEstoque(quantidade,codigo);
        }catch (NullPointerException erro){
            System.out.println(erro.getMessage());
        }
    }
    public Produto consultarProduto(int codigo) {
        for (Produto produto : estoque.getProdutosDisponiveis().keySet()) {
            if (produto.getCodigoProduto() == codigo) {
                return produto;
            }
        }
        throw new NullPointerException("Produto não está no estoque");
    }

    public void vender(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade){
       try {
           Venda venda = new Venda();
           venda.setVendedor(vendedor);
           venda.setCliente(cliente);
           venda.setProduto(produto);
           estoque.retirarUnidadeProdutoEstoque(produto.getCodigoProduto(),quantidade);
           venda.setQuantidade(quantidade);
           venda.setTotalCompra(produto.getPreco()*quantidade);
           vendasController.adicionarVenda(venda);
           System.out.println("--------------NOTA FISCAL------------------");
           System.out.println(venda.mostrarVenda());
           System.out.println("--------------------------------------");
       }catch (NullPointerException | NumberFormatException erro){
           System.out.println(erro.getMessage());
       }
    }

    public void listarProdutos(){
        if (verificaDia()){
            for (Produto produto:estoque.getProdutosComDesconto().keySet()) {
                System.out.println(produto.mostrarProduto());
                System.out.println();
            }
        }else {
            for (Produto produto:estoque.getProdutosDisponiveis().keySet()) {
                System.out.println(produto.mostrarProduto());
                System.out.println();
            }
        }

    }

    public void listarVendas(){
        try {
            System.out.println("------------------LISTA DE VENDAS------------------");
            for (Venda venda: vendasController.getVendas()) {
                System.out.println(venda.mostrarVenda());
                System.out.println();
            }
        }catch ( NullPointerException listaVendaVazia){
            System.out.println(listaVendaVazia.getMessage());
        }

    }
    public void listarVendedores(){
        try {
            System.out.println("------------------LISTA DE VENDEDORES------------------");
            for (Vendedor vendedor: vendedorController.getVendedores()){
                System.out.println(vendedor.mostrar());
                System.out.println();
            }
        }catch ( NullPointerException listaVendedoresVazia){
            System.out.println(listaVendedoresVazia.getMessage());
        }

    }
    public void listarClientes(){
        try {
            System.out.println("------------------LISTA DE CLIENTES------------------");
            for (Cliente cliente: clientesController.getClientes()) {
                System.out.println(cliente.mostrar());
                System.out.println();
            }
        }catch (NullPointerException listaClientesvazio){
            System.out.println(listaClientesvazio.getMessage());
        }


    }
    public void comprasPorCliente(String cpf){
        try {
            for (Venda venda: vendasController.getVendas()) {
                if (venda.getCliente().getCpf().equals(cpf)){
                    System.out.println(venda.mostrarVenda());
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Cliente não encontrado");
        }
    }
    public void comprasPorVendedor(String email){
        try {
            for (Venda venda: vendasController.getVendas()) {
                if (venda.getVendedor().getEmail().equals(email)){
                    System.out.println( venda.mostrarVenda());
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Vendedor não encontrado");
        }
    }
    public Vendedor turnoDaVez(){
        Random random = new Random();
        int idvendedoraleatorio = random.nextInt(Vendedor.getContidVendedor())+1;
        for (Vendedor vendedor: vendedorController.getVendedores()){
            if (vendedor.getIdVendedor()==idvendedoraleatorio) return vendedor;
        }
        throw new NullPointerException("Nenhum vendedor cadastrado");
    }
    public void exibirEstoque(){
        if (verificaDia()){
            for (Produto key:estoque.getProdutosComDesconto().keySet()) {
                System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoque.getProdutosComDesconto().get(key));
                System.out.println();
            }
        }else {
            for (Produto key:estoque.getProdutosDisponiveis().keySet()) {
                System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoque.getProdutosDisponiveis().get(key));
                System.out.println();
            }
        }

    }
    public void adicionarDesconto(double desconto,String data){
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAlteracao = LocalDate.parse(data,formatarData);
        if (desconto>=100) throw new IllegalArgumentException("A porcentagem deve estar entre 1 e 100");
        this.existeDesconto = true;
        desconto = 1-(desconto/100);
        for (Produto produto:estoque.getProdutosComDesconto().keySet()) {
            produto.setPreco(produto.getPreco()*desconto);
        }
    }
    public boolean verificaDia(){

        if (this.existeDesconto && dataAlteracao.isAfter(LocalDate.now())) return true;
        else {
            this.existeDesconto = false;
            return false;
        }

    }

}
