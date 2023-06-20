package Repository;


import Model.Cliente;
import View.IniciarPrograma;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private List<Cliente> clientes =new ArrayList<>();



    public List<Cliente> getClientes() {
        if (clientes.isEmpty())throw new NullPointerException("Lista de clientes vazia");
        return clientes;
    }
    public boolean clienteJaExiste(Cliente cliente){
        for (Cliente c:clientes) {
            if (c.getCpf().equals(cliente.getCpf())||c.getEmail().equals(cliente.getEmail())) return true;
        }
        return false;
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public Cliente procuraClienteEmail(String email,String senha) {
        for (Cliente c:clientes) {
            if (c.getEmail().equals(email)&& c.getSenha().equals(senha))return c;
        }
        return null;
    }

}
