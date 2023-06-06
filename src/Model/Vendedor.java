package Model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa{

    static int contidVendedor = 0;
    private int idVendedor;
    private List<Vendedor> vendedores = new ArrayList<>();

    public Vendedor() {
    }
    public Vendedor(String nome, String cpf, String email, int idade,String senha) {
        super(nome, cpf, email, idade,senha);
        Vendedor.contidVendedor++;
        this.idVendedor = contidVendedor;
    }

    public List<Vendedor> getVendedores() {
        if (vendedores.isEmpty())throw new NullPointerException("Lista de vendedores vazia");
        return vendedores;
    }

    public void adicionarVendedor(Vendedor vendedor){
        for (Vendedor v:vendedores) {
            if (v.getCpf().equals(vendedor.getCpf())||v.getEmail().equals(vendedor.getEmail())) throw new PessoaCadastrada("Vendedor já cadastrado");
        }
        vendedores.add(vendedor);
    }

    public static int getContidVendedor() {
        return contidVendedor;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    @Override
    public String mostrar() {
        return "Código de identificação: "+idVendedor+"\n"+super.mostrar();
    }
    public Vendedor procuraVendedorEmail(String email,String senha) {
        for (Vendedor v:vendedores) {
            if (v.getEmail().equals(email)&& v.getSenha().equals(senha))return v;
        }
        return null;
    }
}
