import java.util.Objects;

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

    public void setCpf(String cpf) {
        if (validaCPF(cpf)) this.cpf = cpf;
        else throw new IllegalArgumentException("Confira se seu CPF está correto");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new IllegalArgumentException("O email deve estar no formato nome@exemple.com");
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public static boolean validaCPF(String CPF){
        if (!(CPF.matches("[1-9.-]+"))){return false;}
        //tirar caractere especial
         CPF = CPF.replace("-","");
         CPF = CPF.replace(".","");
        System.out.println(CPF);
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
            return false;

        char dig10, dig11;
        int somDigitos, i, r, num, peso;
            somDigitos = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                somDigitos = somDigitos + (num * peso);
                peso =- 1;
            }
            r = 11 - (somDigitos % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);
            somDigitos = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                somDigitos = somDigitos + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (somDigitos % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);
        return (dig10 == CPF.charAt(9)) || (dig11 == CPF.charAt(10));
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
