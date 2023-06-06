package Model;

import java.util.Objects;
import Controller.*;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String email;
    protected int idade;
    protected String senha;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email, int idade,String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
            EntradasController.validaCPF(cpf);
            EntradasController.formataCPF(cpf);
            this.cpf = cpf;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
            EntradasController.verificaEmail(email);
            this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade<18) throw new NumberFormatException("Para se cadastar deve ter mais de 16 anos");
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
