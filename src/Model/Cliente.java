package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Cliente> clientes =new ArrayList<>();

    public Cliente(String nome, String cpf, String email, int idade) {
        super(nome, cpf, email, idade);
    }

    public Cliente() {
    }

    public List<Cliente> getClientes() {
        if (clientes.isEmpty())throw new NullPointerException("Lista de clientes vazia");
        return clientes;
    }

    public void adicionarCliente(Cliente cliente){
        for (Cliente c:clientes) {
            if (c.getCpf().equals(cliente.getCpf())||c.getEmail().equals(cliente.getEmail())) throw new RuntimeException("Cliente já cadastrado");
        }
        clientes.add(cliente);
    }
    public Cliente procuraClienteEmail(String email) {
        for (Cliente c:clientes) {
            if (c.getEmail().equals(email))return c;
        }
        return null;
    }
}
