package View;

import Controller.EntradasController;
import Controller.LojaController;
import Model.Cliente;
import Model.Produto;
import Model.Vendedor;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.err;
import static java.lang.System.exit;

public class Loja {
    private Scanner sc = new Scanner(System.in);
    private static final String nomeLoja = "Master Lojas";
    private HashMap<String,String> opcoesTipoMenu;
    private HashMap<String,String> opcoesTipoMenuVendedor;
    private HashMap<String,String> opcoesTipoMenuCliente;
    private LojaController lojaController = new LojaController();
    private Cliente cliente;
    private Vendedor vendedor;
    private int opcao;

    public Loja() {
        inicializarMenu();
    }

    private void inicializarMenu(){
        opcoesTipoMenu = new LinkedHashMap<>();
        opcoesTipoMenu.put("1","Cadastrar Cliente");
        opcoesTipoMenu.put("2","Cadastrar Vendedor");
        opcoesTipoMenu.put("3","Login");
        opcoesTipoMenu.put("4","Sair");

        opcoesTipoMenuVendedor = new LinkedHashMap<>();
        opcoesTipoMenuVendedor.put("1","Cadastrar Produto");
        opcoesTipoMenuVendedor.put("2","Atualizar Produto");
        opcoesTipoMenuVendedor.put("3","Adicionar desconto aos Produto");
        opcoesTipoMenuVendedor.put("4","Listar Vendas");
        opcoesTipoMenuVendedor.put("5","Listar Vendedores");
        opcoesTipoMenuVendedor.put("6","Listar Clientes");
        opcoesTipoMenuVendedor.put("7","Buscar clientes por CPF");
        opcoesTipoMenuVendedor.put("8","Buscar vendedores por email");
        opcoesTipoMenuVendedor.put("9","Checar estoque");
        opcoesTipoMenuVendedor.put("10","Voltar ao menu principal");
        opcoesTipoMenuVendedor.put("11","Sair");

        opcoesTipoMenuCliente = new LinkedHashMap<>();
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
                opcao = lojaController.verificaOpcaoMenu(sc.nextLine(),listaExibir);
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
                       EntradasController.verificaEmail(email);
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
            }catch (IllegalArgumentException erro){
                System.out.println(erro.getMessage());
                sc.nextLine();
            }catch (NullPointerException erro){
                System.out.println(erro.getMessage());
                menuPrincipal();
            }
        }
    }

    public void menuFluxoCliente() {
        sc.nextLine();
        vendedor = lojaController.turnoDaVez();
        System.out.println("Olá sou o(a) " + vendedor.getNome() + ",serei responsável pela sua venda!");
        while (true){
            mostrarOpcoes(opcoesTipoMenuCliente);
            try {
                switch (opcao) {
                    case 1 -> lojaController.listarProdutos();
                    case 2 -> {
                        Produto produto;
                        int quantidade;
                        System.out.println("Digite o código do produto: ");
                        produto = lojaController.consultarProduto(EntradasController.verificaQuantidade(sc.next()));
                        System.out.println("Quantas unidade de " + produto.getNome()+ " deseja comprar?");
                        quantidade = EntradasController.verificaQuantidade(sc.next());
                        sc.nextLine();
                        lojaController.vender(vendedor, cliente, produto, quantidade);
                        lojaController.listarVendas();
                    }
                    case 3->menuPrincipal();
                    case 4-> exit(0);
                }
                if (continuar()) menuFluxoCliente();
                else exit(0);

            }catch (NullPointerException | IllegalArgumentException eror){
                sc.nextLine();
                System.out.println(eror.getMessage());
            }
        }

    }
    public void menuFluxoVendedor(){
        sc.nextLine();
        mostrarOpcoes(opcoesTipoMenuVendedor);
        System.out.println("Bem vindo(a) "+vendedor.getNome());
        while (true){
            try {
                switch (opcao) {
                    case 1 -> lerInformacoesProduto();
                    case 2 -> {
                        System.out.println("Digite o código do produto: ");
                        int codigo = EntradasController.verificaQuantidade(sc.next());
                        System.out.println("Digite a quantidade a ser adicionada ao estoque: ");
                        int quantidade = EntradasController.verificaQuantidade(sc.next());
                        lojaController.atualizaQtdProduto(quantidade, codigo);
                    }
                    case 3-> {
                        System.out.println("Digite a porcentagem do desconto: ");
                        double desconto = EntradasController.verificaPreco(sc.next());
                        System.out.println("Digite até que dia esse desconto será valido: (Formato esperado: dd/mm/yyyy)");
                        String data =sc.next();
                        lojaController.adicionarDesconto(desconto,data);
                    }
                    case 4 -> lojaController.listarVendas();

                    case 5 -> lojaController.listarVendedores();
                    case 6 -> lojaController.listarClientes();
                    case 7-> {
                        System.out.println("Digite o cpf do cliente desejado");
                        String cpf = sc.next();
                        EntradasController.validaCPF(cpf);
                        lojaController.comprasPorCliente(cpf);
                    }
                    case 8 -> {
                        System.out.println("Digite o email do vendedor desejado");
                        String email = sc.next();
                        EntradasController.verificaEmail(email);
                        lojaController.comprasPorVendedor(email);
                    }
                    case 9-> {
                        System.out.println("------------------Listar Estoque------------------");
                        lojaController.exibirEstoque();
                    }
                    case 10-> menuPrincipal();
                    case 11-> exit(0);
                }
                if (continuar()) menuFluxoVendedor();
                else exit(0);
            }catch (DateTimeParseException error){
                System.out.println("Data inválida.Digite no formato pedido!");
            }catch (IllegalArgumentException error){
                sc.nextLine();
                System.out.println(error.getMessage());
            }
        }
    }

    public Cliente lerInformacoesCliente(){
            cliente = new Cliente();
            System.out.println("------------CADASTRAR CLIENTE------------------");
            System.out.println("Digite seu nome: ");
            cliente.setNome(EntradasController.formataNome(sc.nextLine()));
            System.out.println("Digite seu CPF: ");
            cliente.setCpf(sc.next());
            System.out.println("Digite seu email: ");
            cliente.setEmail( sc.next());
            System.out.println("Digite sua idade: ");
            cliente.setIdade(EntradasController.verificaQuantidade(sc.next()));
        return cliente;
    }

    public Vendedor lerInformacoesVendedor(){
        vendedor = new Vendedor();
        System.out.println("------------CADASTRAR VENDEDOR------------------");
        System.out.println("Digite seu nome: ");
        vendedor.setNome(EntradasController.formataNome(sc.nextLine()));
        System.out.println("Digite seu CPF: ");
        vendedor.setCpf(EntradasController.formataCPF(sc.next()));
        System.out.println("Digite seu email: ");
        vendedor.setEmail( sc.next());
        System.out.println("Digite sua idade: ");
        vendedor.setIdade( EntradasController.verificaQuantidade(sc.next()));
        sc.nextLine();
        return vendedor;
    }
    public void lerInformacoesProduto(){
        System.out.println("------------CADASTRAR PRODUTO------------------");
        System.out.println("Digite o nome do produto: ");
        String nome = EntradasController.formataNome(sc.nextLine());
        System.out.println("Digite a descricao: ");
        String descricao = sc.nextLine();
        System.out.println("Digite a categoria: ");
        String categoria = sc.nextLine();
        System.out.println("Digite a quantidade: ");
        int quantidade = EntradasController.verificaQuantidade(sc.next());
        System.out.println("Digite o preço: ");
        double preco = EntradasController.verificaPreco(sc.next());
        lojaController.cadastrarNovoProduto(nome,preco,descricao,categoria,quantidade);
    }

    public boolean continuar(){
        System.out.println("Deseja continuar?s-sim n-não");
        while (true){
            switch (sc.next()) {
                case "s" -> {
                    return true;
                }
                case "n" -> {
                    return false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

}
