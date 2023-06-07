package Controller;

import Model.Cliente;
import Model.Venda;
import View.IniciarPrograma;

public class ClienteController {
    private Cliente cliente = new Cliente();
    private VendaController vendasController;
    private IniciarPrograma iniciarPrograma = new IniciarPrograma(cliente);

    public ClienteController(VendaController vendaController) {
        this.vendasController = vendaController;
        iniciarPrograma.iniciarBDCliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void cadastrarCliente(Cliente cliente){
        this.cliente.adicionarCliente(cliente);
    }
    public void listarClientes(){
        try {
            System.out.println("------------------LISTA DE CLIENTES------------------");
            for (Cliente cliente:this.cliente.getClientes()) {
                System.out.println(cliente.mostrar());
                System.out.println();
            }
        }catch (NullPointerException listaClientesvazio){
            System.out.println(listaClientesvazio.getMessage());
        }
    }
    public void comprasPorCliente(String cpf){
        try {
            for (Venda venda: vendasController.getVenda().getVendas()) {
                if (venda.getCliente().getCpf().equals(cpf)){
                    System.out.println(venda.mostrarVenda());
                    System.out.println();
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Cliente não encontrado");
        }
    }
    public Cliente verificaCredenciais(String email,String senha){
        return cliente.procuraClienteEmail(email,senha);
    }
}
