import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vendedor extends Pessoa{

    static int contidVendedor = 0;
    private int idVendedor;
    private List<Vendedor> vendedores = new ArrayList<>();

    public Vendedor() {

    }

    public Vendedor(String nome, String cpf, String email, int idade) {
        super(nome, cpf, email, idade);
        Vendedor.contidVendedor++;
        this.idVendedor = contidVendedor;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void adicionarVendedor(Vendedor vendedor){
        for (Vendedor v:vendedores) {
            if (v.getCpf().equals(vendedor.getCpf())||v.getEmail().equals(vendedor.getEmail())) throw new IllegalArgumentException("Cliente já cadastrado");
        }
        vendedores.add(vendedor);
    }

    public static int getContidVendedor() {
        return contidVendedor;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor() {
        Vendedor.contidVendedor++;
        this.idVendedor = contidVendedor;
    }

    @Override
    public String mostrar() {
        return "Código de identificação: "+idVendedor+"\n"+super.mostrar();
    }
    public Vendedor procuraVendedorEmail(String email) {
        for (Vendedor v:vendedores) {
            if (v.getEmail().equals(email))return v;
        }
        return null;
    }
}
