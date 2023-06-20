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



}
