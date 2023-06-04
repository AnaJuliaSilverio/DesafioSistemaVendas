import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.exit;

public class LojaT {
    private Scanner sc = new Scanner(System.in);
    private static final String nomeLoja = "Master Lojas";
    private HashMap<String,String> opcoesTipoMenu;
    private HashMap<String,String> opcoesTipoMenuVendedor;
    private HashMap<String,String> opcoesTipoMenuCliente;
    private LojaController lojaController = new LojaController();
    private Cliente cliente;
    private Vendedor vendedor;
    private int opcao;

    public LojaT() {
        inicializarMenu();
    }

    private void inicializarMenu(){
        opcoesTipoMenu = new HashMap<>();
        opcoesTipoMenu.put("1","Cadastrar Cliente");
        opcoesTipoMenu.put("2","Cadastrar Vendedor");
        opcoesTipoMenu.put("3","Login");
        opcoesTipoMenu.put("4","Sair");

        opcoesTipoMenuVendedor = new HashMap<>();
        opcoesTipoMenuVendedor.put("1","Cadastrar Produto");
        opcoesTipoMenuVendedor.put("2","Atualizar Produto");
        opcoesTipoMenuVendedor.put("3","Listar Vendas");
        opcoesTipoMenuVendedor.put("4","Listar Vendedores");
        opcoesTipoMenuVendedor.put("5","Listar Clientes");
        opcoesTipoMenuVendedor.put("6","Buscar clientes por CPF");
        opcoesTipoMenuVendedor.put("7","Buscar vendedores por email");
        opcoesTipoMenuVendedor.put("8","Checar estoque");
        opcoesTipoMenuVendedor.put("9","Voltar ao menu principal");
        opcoesTipoMenuVendedor.put("10","Sair");

        opcoesTipoMenuCliente = new HashMap<>();
        opcoesTipoMenuCliente.put("1","Listar Produtos");
        opcoesTipoMenuCliente.put("2","Comprar");
        opcoesTipoMenuCliente.put("3","Voltar ao menu principal");
        opcoesTipoMenuCliente.put("4","Sair");

    }
    private void mostrarOpcoes(HashMap<String,String> listaExibir){
        while (true){
            try {
                for (String chave:listaExibir.keySet()) {
                    System.out.println(chave+"-"+listaExibir.get(chave));
                }
                opcao = lojaController.verificaOpcaoMenu(sc.nextLine(),opcoesTipoMenu);
                break;
            }catch (IllegalArgumentException erro){
                System.out.println(erro.getMessage());
            }
        }

    }
    public void menuPrincipal(){
        System.out.println("Bem vindo(a) a "+nomeLoja);
       mostrarOpcoes(opcoesTipoMenu);
        while (true){
            try{
                switch (opcao) {
                    case 1 -> {
                        lojaController.cadastrarCliente(lerInformacoesCliente());
                        menuFluxoCliente();
                    }
                    case 2 -> {
                        lojaController.cadastrarVendedor(lerInformacoesVendedor());
                        menuFluxoVendedor();
                    }
                    case 3 -> {
                        System.out.println("Digite seu email: ");
                        String email = sc.next();
                        if (lojaController.verificaCredencial(email) instanceof Cliente){
                            cliente = (Cliente) lojaController.verificaCredencial(email);
                            menuFluxoCliente();
                        }
                        else if (lojaController.verificaCredencial(email) instanceof Vendedor){
                            vendedor = (Vendedor)  lojaController.verificaCredencial(email);
                            menuFluxoVendedor();
                        }

                    }
                    case 4->exit(0);
                }
                break;
            }catch (IllegalArgumentException|NullPointerException erroOpcaoMenu){
                System.out.println(erroOpcaoMenu.getMessage());
            }
        }
    }

    public void menuFluxoCliente() {
        vendedor = lojaController.turnoDaVez();
        sc.nextLine();
        mostrarOpcoes(opcoesTipoMenuCliente);
        System.out.println("Ola sou o(a) " + vendedor.getNome() + ",serei responsável pela sua venda!");
        while (true){
            try {
                switch (opcao) {
                    case 1 -> lojaController.listarProdutos();
                    case 2 -> {
                        Produto produto;
                        int quantidade;

                        System.out.println("Digite o código do produto: ");
                        produto = lojaController.consultarProduto(sc.nextInt());
                        System.out.println("Quantas unidade de " + produto.getNome()+ " deseja comprar?");
                        quantidade = sc.nextInt();
                        sc.nextLine();
                        lojaController.vender(vendedor, cliente, produto, quantidade);

                    }
                    case 3->menuPrincipal();
                    case 4-> exit(0);
                }
                break;
            }catch (NullPointerException | IllegalArgumentException eror){
                System.out.println(eror.getMessage());
            }
        }

    }
    public void menuFluxoVendedor(){
        mostrarOpcoes(opcoesTipoMenuVendedor);
        while (true){
            try {
                switch (opcao) {
                    case 1 -> lerInformacoesProduto();
                    case 2 -> {
                        System.out.println("Digite o código do produto: ");
                        int codigo = sc.nextInt();
                        System.out.println("Digite a quantidade a ser adicionada ao estoque: ");
                        int quantidade = sc.nextInt();
                        lojaController.atualizaQtdProduto(quantidade, codigo);
                    }
                    case 3 -> {
                        System.out.println("------------------LISTA DE VENDEDAS------------------");

                        lojaController.listarVendas();
                    }
                    case 4 -> {
                        System.out.println("------------------LISTA DE VENDEDORES------------------");
                        lojaController.listarVendedores();
                    }
                    case 5 -> {
                        System.out.println("------------------LISTA DE CLIENTES------------------");
                        lojaController.listarClientes();
                    }
                    case 6 -> {
                        System.out.println("Digite o cpf do cliente desejado");
                        lojaController.comprasPorCliente(sc.next());
                    }
                    case 7 -> {
                        System.out.println("Digite o email do vendedor desejado");
                        lojaController.comprasPorVendedor(sc.next());
                    }
                    case 8 -> {
                        System.out.println("------------------Listar Estoque------------------");
                        lojaController.exibirEstoque();
                    }
                    case 9-> menuPrincipal();
                    case 10-> exit(0);
                }
                break;
            }catch (NullPointerException error){
                System.out.println(error.getMessage());
            }
        }
    }

    public Cliente lerInformacoesCliente(){
        cliente = new Cliente();
        System.out.println("------------CADASTRAR CLIENTE------------------");
        System.out.println("Digite seu nome: ");
        cliente.setNome(FormataValores.formataNome(sc.nextLine()));
        System.out.println("Digite seu CPF: ");
        cliente.setCpf(FormataValores.formataCPF(sc.next()));
        System.out.println("Digite seu email: ");
        cliente.setEmail( sc.next());
        System.out.println("Digite sua idade: ");
        cliente.setIdade( sc.nextInt());
        sc.nextLine();
        return cliente;
    }

    public Vendedor lerInformacoesVendedor(){
        vendedor = new Vendedor();
        System.out.println("------------CADASTRAR VENDEDOR------------------");
        System.out.println("Digite seu nome: ");
        vendedor.setNome(sc.next());
        System.out.println("Digite seu CPF: ");
        vendedor.setCpf(sc.next());
        System.out.println("Digite seu email: ");
        vendedor.setEmail( sc.next());
        System.out.println("Digite sua idade: ");
        vendedor.setIdade( sc.nextInt());
        sc.nextLine();
        return vendedor;

    }
    public void lerInformacoesProduto(){
        System.out.println("------------CADASTRAR PRODUTO------------------");
        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.println("Digite a descricao: ");
        String descricao = sc.next();
        System.out.println("Digite a categoria: ");
        String categoria = sc.next();
        System.out.println("Digite a quantidade: ");
        int quantidade = sc.nextInt();
        System.out.println("Digite o preço: ");
        double preco = sc.nextDouble();
        lojaController.cadastrarNovoProduto(nome,preco,descricao,categoria,quantidade);
    }

}
