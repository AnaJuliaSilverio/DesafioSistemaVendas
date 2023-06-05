package Controller;

public class FormataValores {

    public static String formataNome(String nome){
        nome = nome.trim();
        nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
        for (int i = 0; i < nome.length(); i++) {
            if (nome.charAt(i)==' '){
                nome = nome.replace(nome.charAt(i+1),Character.toUpperCase(nome.charAt(i+1)));
            }
        }
        return nome;
    }
    public static String formataCPF(String cpf){
        if (!(cpf.contains(".")&&cpf.contains("-"))){
          cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        }
        return cpf;
    }

    public static String formataPreco(double preco){
        preco =Math.round(preco);
        String precoFormatado = Double.toString(preco);
        precoFormatado = precoFormatado.replace(".",",");
        precoFormatado = "R$"+precoFormatado+"0";
        return precoFormatado;

    }
    public static void validaCPF(String CPF) {
        if (!(CPF.matches("[1-9.-]+"))){throw new IllegalArgumentException("FORMATO DO CPF INVALIDO");}
        //tirar caractere especial
        CPF = CPF.replace("-","");
        CPF = CPF.replace(".","");
        System.out.println(CPF);
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
            throw new IllegalArgumentException("CPF INVALIDO");

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
       if (!((dig10 == CPF.charAt(9)) || (dig11 == CPF.charAt(10)))) throw new IllegalArgumentException("CPF INVALIDO");
    }
    public static void verificaEmail(String email){
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new IllegalArgumentException("EMAIL INVALIDO");
    }
}
