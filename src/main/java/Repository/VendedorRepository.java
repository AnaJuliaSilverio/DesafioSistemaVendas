package Repository;

import Model.PessoaCadastrada;
import Model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class VendedorRepository {
    private List<Vendedor> vendedores = new ArrayList<>();
    public List<Vendedor> getVendedores() {
        if (vendedores.isEmpty())throw new NullPointerException("Lista de vendedores vazia");
        return vendedores;
    }
    public boolean vendedorJaExiste(Vendedor vendedor){
        for (Vendedor v:vendedores) {
            if (v.getCpf().equals(vendedor.getCpf())||v.getEmail().equals(vendedor.getEmail())) return true;
        }
        return false;
    }

    public void adicionarVendedor(Vendedor vendedor){
        vendedores.add(vendedor);
    }
    public Vendedor procuraVendedorEmail(String email,String senha) {
        for (Vendedor v:vendedores) {
            if (v.getEmail().equals(email)&& v.getSenha().equals(senha))return v;
        }
        return null;
    }
}
