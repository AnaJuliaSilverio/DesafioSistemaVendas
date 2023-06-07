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
    private ClienteController clienteController = new ClienteController(vendaController);
    private VendedorController vendedorController = new VendedorController(vendaController);
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
                        menuFluxoCliente();
                    }
                    case 2 -> {
                        vendedorView.cadastrarVendedor();
                        menuFluxoVendedor();
                    }
                    case 3 ->{
                        String retorno = pessoaView.login();
                        if (retorno.equals("cliente")) menuFluxoCliente();
                        else if (retorno.equals("vendedor")) menuFluxoVendedor();
                    }
                    case 4->exit(0);
                }
                break;
            }
            catch (IllegalArgumentException erro){
                System.out.println(erro.getMessage());
                sc.nextLine();
            }catch (NullPointerException | PessoaCadastrada erro){
                System.out.println(erro.getMessage());
                sc.nextLine();
                menuPrincipal();
            }
        }
    }

    public void menuFluxoCliente() {
        vendedorView.setVendedor();
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
                    case 4 -> vendaView.listarVendas();
                    case 5 -> vendedorView.listarVendedores();
                    case 6 -> clienteView.listarClientes();
                    case 7-> clienteView.buscarClientesPorCpf();
                    case 8 -> vendedorView.buscarVendedorPorEmail();
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
