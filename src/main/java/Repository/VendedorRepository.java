package Repository;

import Model.PessoaCadastrada;
import Model.Vendedor;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
        criptografarSenha(vendedor);
        vendedores.add(vendedor);
    }
    public void criptografarSenha(Vendedor vendedor){
        String hashSenha = BCrypt.hashpw(vendedor.getSenha(), BCrypt.gensalt());
        vendedor.setSenha(hashSenha);
    }
    public Vendedor procuraVendedorEmail(String email,String senha) {
        for (Vendedor v:vendedores) {
            if (v.getEmail().equals(email)&& verificaSenhaCriptografada(v,senha))return v;
        }
        return null;
    }
    public boolean verificaSenhaCriptografada(Vendedor vendedor,String senha){
        return BCrypt.checkpw(senha,vendedor.getSenha());
    }
}
