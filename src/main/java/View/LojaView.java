package View;
import Controller.*;
import Model.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static java.lang.System.exit;

public class LojaView {
    private static final String nomeLoja = "Master Lojas";
    private Scanner sc = new Scanner(System.in);
    private EstoqueController estoqueController = new EstoqueController();
    private VendaController vendaController = new VendaController(estoqueController);
    private ClienteController clienteController = new ClienteController();
    private VendedorController vendedorController = new VendedorController();
    ClienteView clienteView = new ClienteView(clienteController);
    EstoqueView estoqueView = new EstoqueView(estoqueController);
    VendedorView vendedorView = new VendedorView(vendedorController);
    VendaView vendaView = new VendaView(vendaController,estoqueController);
    private MenuView menuView = new MenuView();
    private PessoaView pessoaView = new PessoaView(clienteController,vendedorController,vendedorView,clienteView);
    public void menuPrincipal(){
        System.out.println("Bem vindo(a) a "+nomeLoja);
        menuView.mostrarOpcoes(menuView.getOpcoesTipoMenu());
        while (true){
            try{
                switch (menuView.getOpcao()) {
                    case 1 -> {
                        clienteView.cadastrarCliente();
                        vendedorView.setVendedor();
                        menuFluxoCliente();
                    }
                    case 2 -> {
                        vendedorView.cadastrarVendedor();
                        menuFluxoVendedor();
                    }
                    case 3 ->{
                        String retorno = pessoaView.login();
                        switch (retorno) {
                            case "duplo" -> {
                                System.out.println("Deseja entrar como cliente ou vendedor?");
                                String resposta = sc.next();
                                if (resposta.equalsIgnoreCase("cliente")) menuFluxoCliente();
                                else if (resposta.equalsIgnoreCase("vendedor")) menuFluxoVendedor();
                                else {
                                    System.out.println("Resposta inválida");
                                    menuPrincipal();
                                }
                            }
                            case "cliente" ->{
                                vendedorView.setVendedor();
                                menuFluxoCliente();}
                            case "vendedor" -> menuFluxoVendedor();
                        }
                    }
                    case 4->exit(0);
                }
                break;
            }
            catch (IllegalArgumentException erro){
                System.out.println(erro.getMessage());
            }catch (NullPointerException | PessoaCadastrada erro){
                System.out.println(erro.getMessage());
                menuPrincipal();
            }
        }
    }

    public void menuFluxoCliente() {
        System.out.println("Olá sou o(a) " + vendedorView.getVendedor().getNome() + ",serei responsável pela sua venda!");
        while (true){
            menuView.mostrarOpcoes(menuView.getOpcoesTipoMenuCliente());
            try {
                switch (menuView.getOpcao()) {
                    case 1 -> estoqueView.exibirProduto();
                    case 2 ->vendaView.realizarVenda(vendedorView.getVendedor(),clienteView.getCliente());
                    case 3->menuPrincipal();
                    case 4-> exit(0);
                }
                if (menuView.continuar()) menuFluxoCliente();
                else exit(0);
            }catch (NullPointerException | IllegalArgumentException eror){
                System.out.println(eror.getMessage());
            }
        }
    }
    public void menuFluxoVendedor(){
        System.out.println("Bem vindo(a) "+vendedorView.getVendedor().getNome());
        menuView.mostrarOpcoes(menuView.getOpcoesTipoMenuVendedor());
        while (true){
            try {
                switch (menuView.getOpcao()) {
                    case 1 -> estoqueView.cadastrarProduto();
                    case 2 -> estoqueView.atualizarProduto();
                    case 3-> estoqueView.aplicarDesconto();
                    case 4-> vendaView.listarVendas();
                    case 5->vendedorView.listarVendedores();
                    case 6->clienteView.listarClientes();
                    case 7->vendaView.listarClientesPorCpf();
                    case 8-> vendaView.listarVendedorPorEmail();
                    case 9-> estoqueView.exibirEstoque();
                    case 10-> menuPrincipal();
                    case 11-> exit(0);
                }
                if (menuView.continuar()) menuFluxoVendedor();
                else exit(0);
            }catch (DateTimeParseException error){
                System.out.println("Data inválida.Digite no formato pedido!");
            }catch (IllegalArgumentException error){
                System.out.println(error.getMessage());
            }
        }
    }
}
