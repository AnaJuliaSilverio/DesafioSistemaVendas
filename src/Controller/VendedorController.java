package Controller;

import Controller.VendaController;
import Model.Venda;
import Model.Vendedor;
import View.IniciarPrograma;

import java.util.Random;

public class VendedorController {
    private Vendedor vendedor =new Vendedor();
    private VendaController vendasController;
    private IniciarPrograma iniciarPrograma = new IniciarPrograma(vendedor);
    public VendedorController(VendaController vendasController) {
        this.vendasController = vendasController;
        iniciarPrograma.iniciarBDVendedor();
    }

    public void cadastrarVendedor(Vendedor vendedor){
        this.vendedor.adicionarVendedor(vendedor);
    }

    public Vendedor turnoDaVez(){
        Random random = new Random();
        int idvendedoraleatorio = random.nextInt(vendedor.getVendedores().size())+1;
        for (Vendedor vendedor: vendedor.getVendedores()){
            if (vendedor.getIdVendedor()==idvendedoraleatorio) return vendedor;
        }
        throw new NullPointerException("Nenhum vendedor cadastrado");
    }
    public void comprasPorVendedor(String email){
        try {
            for (Venda venda: vendasController.getVenda().getVendas()) {
                if (venda.getVendedor().getEmail().equals(email)){
                    System.out.println( venda.mostrarVenda());
                    System.out.println();
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Vendedor não encontrado");
        }
    }
    public Vendedor verificaCredenciais(String email,String senha){
        return vendedor.procuraVendedorEmail(email,senha);
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
}
