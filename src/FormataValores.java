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
}
