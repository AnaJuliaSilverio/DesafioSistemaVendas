package View;

import Controller.EntradasController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class MenuView {
    private HashMap<String,String> opcoesTipoMenu;
    private HashMap<String,String> opcoesTipoMenuVendedor;
    private HashMap<String,String> opcoesTipoMenuCliente;
    private int opcao;
    private Scanner sc = new Scanner(System.in);
    public MenuView() {
        inicializarMenu();
    }
    public void mostrarOpcoes(HashMap<String,String> listaExibir){
        while (true){
            try {
                for (String chave:listaExibir.keySet()) {
                    System.out.println(chave+"-"+listaExibir.get(chave));
                }
                 opcao = EntradasController.verificaOpcaoMenu(EntradasController.verificaBufferScaner(sc.nextLine()), listaExibir);
                break;
            }catch (IllegalArgumentException erro){
                System.out.println(erro.getMessage());
            }
        }
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

    public int getOpcao() {
        return opcao;
    }


    public HashMap<String, String> getOpcoesTipoMenu() {
        return opcoesTipoMenu;
    }
    public HashMap<String, String> getOpcoesTipoMenuVendedor() {
        return opcoesTipoMenuVendedor;
    }

    public HashMap<String, String> getOpcoesTipoMenuCliente() {
        return opcoesTipoMenuCliente;
    }

    public boolean continuar(){
        while (true){
            System.out.println("Deseja continuar?s-sim n-não");
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
