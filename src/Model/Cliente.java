package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Cliente> clientes =new ArrayList<>();

    public Cliente(String nome, String cpf, String email, int idade, String senha) {
        super(nome, cpf, email, idade, senha);
    }

    public Cliente() {
    }

    public List<Cliente> getClientes() {
        if (clientes.isEmpty())throw new NullPointerException("Lista de clientes vazia");
        return clientes;
    }

    public void adicionarCliente(Cliente cliente){
        for (Cliente c:clientes) {
            if (c.getCpf().equals(cliente.getCpf())||c.getEmail().equals(cliente.getEmail())) throw new PessoaCadastrada("Cliente já cadastrado");
        }
        clientes.add(cliente);
    }
    public Cliente procuraClienteEmail(String email,String senha) {
        for (Cliente c:clientes) {
            if (c.getEmail().equals(email)&& c.getSenha().equals(senha))return c;
        }
        return null;
    }
}
