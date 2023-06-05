package Model;

import java.util.Objects;
import Controller.*;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String email;
    protected int idade;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf)  {
            FormataValores.validaCPF(cpf);
            FormataValores.formataCPF(cpf);
            this.cpf = cpf;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
            FormataValores.verificaEmail(email);
            this.email = email;

    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return idade == pessoa.idade && Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(email, pessoa.email);
    }
    public String mostrar(){
        return "Nome: "+nome+"\nIdade: "+idade+"\nCPF: "+cpf+"\nEmail: "+email;
    }
}
