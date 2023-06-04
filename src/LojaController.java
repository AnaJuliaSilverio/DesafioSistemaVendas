import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LojaController {
    private Cliente clientesController=new Cliente();
    private Scanner sc = new Scanner(System.in);
    private Vendedor vendedorController=new Vendedor();
    private Produto produto;
    private Estoque estoque = new Estoque();
    private Venda vendasController = new Venda();

    public LojaController() {
        IniciarPrograma iniciarPrograma= new IniciarPrograma(vendedorController,clientesController);
    }

    public int verificaOpcaoMenu(String opcao, HashMap<String,String> menu){
        for (String op:menu.keySet()) {
            if (op.equals(opcao)||menu.get(op).equals(opcao)) return Integer.parseInt(op);
        }
        throw new IllegalArgumentException("Por favor digite uma opção válida");
    }
    public void cadastrarCliente(Cliente cliente){
        try {
            clientesController.adicionarCliente(cliente);
        }catch (NullPointerException erro){
            System.out.println("ERRO AO CADASTRAR CLIENTE");
        }
    }

    public void cadastrarVendedor(Vendedor vendedor){
        try {
            vendedorController.adicionarVendedor(vendedor);
        }catch (NullPointerException erro){
            System.out.println("ERRO AO CADASTRAR VENDEDO");
        }
    }

    public Pessoa verificaCredencial(String email){
        if (clientesController.procuraClienteEmail(email)!=null) return clientesController.procuraClienteEmail(email);
        else if (vendedorController.procuraVendedorEmail(email)!=null) return vendedorController.procuraVendedorEmail(email);
        else return null;
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

    public void vender(Vendedor vendedor,Cliente cliente,Produto produto,int quantidade){
       try {
           Venda venda = new Venda();
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

    public void listarProdutos(){
        for (Produto produto:estoque.getProdutosDisponiveis().keySet()) {
            System.out.println(produto.mostrarProduto());
            System.out.println();
        }
    }

    public void listarVendas(){
        for (Venda venda: vendasController.getVendas()) {
            System.out.println(venda.mostrarVenda());
            System.out.println();
        }
    }
    public void listarVendedores(){
        for (Vendedor vendedor: vendedorController.getVendedores()){
            System.out.println(vendedor.mostrar());
            System.out.println();
        }
    }
    public void listarClientes(){
        for (Cliente cliente: clientesController.getClientes()) {
            System.out.println(cliente.mostrar());
            System.out.println();
        }
    }
    public void comprasPorCliente(String cpf){
        try {
            for (Venda venda: vendasController.getVendas()) {
                if (venda.getCliente().getCpf().equals(cpf)){
                    venda.mostrarVenda();
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
                    venda.mostrarVenda();
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
        return null;
    }
    public void exibirEstoque(){
        for (Produto key:estoque.getProdutosDisponiveis().keySet()) {
            System.out.println(key.mostrarProduto() +"\nQuantidade Disponível: "+estoque.getProdutosDisponiveis().get(key));
            System.out.println();
        }
    }

}
