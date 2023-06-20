package Controller;

import Model.PessoaCadastrada;
import Model.Vendedor;
import Repository.VendedorRepository;
import View.IniciarPrograma;

import java.util.Random;

public class VendedorController {

    private VendedorRepository vendedorRepository = new VendedorRepository();

    private IniciarPrograma iniciarPrograma = new IniciarPrograma(vendedorRepository);
    public VendedorController() {
        iniciarPrograma.iniciarBDVendedor();
    }

    public void cadastrarVendedor(Vendedor vendedor){
        if (vendedorRepository.vendedorJaExiste(vendedor))throw new PessoaCadastrada("Vendedor já cadastrado");
        vendedorRepository.adicionarVendedor(vendedor);
    }
    public void listarVendedores(){
            System.out.println("------------------LISTA DE VENDEDORES------------------");
            for (Vendedor vendedor: vendedorRepository.getVendedores()){
                System.out.println(vendedor.mostrar());
                System.out.println();
            }
    }

    public Vendedor turnoDaVez(){
        Random random = new Random();
        int idvendedoraleatorio = random.nextInt(vendedorRepository.getVendedores().size())+1;
        for (Vendedor vendedor: vendedorRepository.getVendedores()){
            if (vendedor.getIdVendedor()==idvendedoraleatorio) return vendedor;
        }
        throw new NullPointerException("Nenhum vendedor cadastrado");
    }

    public Vendedor verificaCredenciais(String email,String senha){
        return vendedorRepository.procuraVendedorEmail(email,senha);
    }


}
