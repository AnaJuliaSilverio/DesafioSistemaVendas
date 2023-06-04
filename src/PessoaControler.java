public class PessoaControler {

    public static void validaCPF(String CPF){
        if (!(CPF.matches("[1-9.-]+"))) throw new IllegalArgumentException("Digite o CPF no formato pedido");
        //tirar caractere especial
        CPF = CPF.replace("-","");
        CPF = CPF.replace(".","");
        System.out.println(CPF);
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
            throw new IllegalArgumentException("CPF inválido");

        char dig10, dig11;
        int somDigitos, i, r, num, peso;

        // Calculo do primeiro digito verificador
        somDigitos = 0;
        peso = 10;
        for (i=0; i<9; i++) {
            //multiplica os 9 primeiros dígitos pela sequência decrescente de números de 10 a 2 e soma os resultados.
            // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(CPF.charAt(i) - 48);
            somDigitos = somDigitos + (num * peso);
            peso =- 1;
        }
        //se o resto da divisao for 2 ou menor que 2
        r = 11 - (somDigitos % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do segundo digito Verificador
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
        // Verifica se os digitos calculados conferem com os digitos informados.
        if (!((dig10 == CPF.charAt(9)) || (dig11 == CPF.charAt(10))))
            throw new IllegalArgumentException("CPF inválido");

    }
}
