# Sistema de Vendas

Este é um sistema responsável por registros de vendas de uma organização fictica "Master Lojas". Ele permite cadastrar vendas,produtos,clientes, vendedores e realizar diversas consultas relacionadas às vendas realizadas e ao estoque.

## Requisitos do Sistema

O sistema deve atender aos seguintes requisitos:

- Permitir o cadastro de vendas no sistema, com os seguintes dados:
    - Vendedor
    - Cliente
    - Código do produto
    - Nome do produto
    - Preço do produto
    - Quantidade do produto
    - Valor total da compra

- Não permitir o cadastro de vendas para clientes não cadastrados.
- Não permitir o cadastro de vendas de vendedores não cadastrados.
- Permitir a listagem de todas as vendas cadastradas.
- Permitir a listagem de todos os vendedores cadastrados.
- Permitir a listagem de todos os clientes cadastrados.
- Não permitir o cadastro de clientes com e-mail inválido (sem @ e sem .).
- Não permitir o cadastro de vendedores com e-mail inválido (sem @ e sem .).
- Não permitir o cadastro de clientes com CPFs repetidos e inválidos.
- Não permitir o cadastro de vendedores com CPFs repetidose inválidos.
- Não permitir o cadastro de clientes com e-mails repetidos.
- Não permitir o cadastro de vendedores com e-mails repetidos.
- Permitir a pesquisa de todas as compras de um cliente específico através do seu CPF.
- Permitir a pesquisa de todas as vendas de um vendedor específico através do seu e-mail.
- Permitir que o usuário saia do sistema.
- Exibir um menu de acesso ao usuário com todas as opções disponíveis.

## Uso do Sistema

1. O usuário terá acesso a um menu de opções.
2. O usuário poderá selecionar uma das opções disponíveis.
3. Dependendo da opção selecionada, o usuário será solicitado a fornecer informações adicionais, como dados da venda, cliente ou vendedor.
4. O sistema realizará as devidas verificações e executará a ação correspondente.
5. O resultado da ação será exibido ao usuário.
6. O usuário poderá escolher outra opção ou sair do sistema.
## Funções e Regras de Negócios

O sistema de vendas possui as seguintes funções e regras de negócios:

1. *Validação de E-mails*
    - O sistema realiza a validação dos e-mails informados durante o cadastro de clientes e vendedores e no momento de consulta.
    - E-mails fora do formato esperado(nome@exemplo.com) não são permitidos, e o sistema exibe uma mensagem de erro.

2. *Validação de CPFs*
    - O sistema realiza a validação dos CPFs informados durante o cadastro de clientes e vendedores e no momento de consultas.
    - CPFs repetidos não são permitido, e o sistema exibe uma mensagem de erro.Além disso,o CPF não pode conter letras,caracteres especias(com exeçao de '.' e '-') e deve ser válido.

3. *Validaçao de Senha*
    - A senha deve contem no mínimo 6 caracteres, sendo um deles uma letra maiúscula e um caracter especial

4. *Validaçao de Quantidades e preços*
    - Todos os valores e quantidades devem ser um valor(inteiro ou decimal) maior que 0,contendo apenas valores numéricos.
5. *Validaçao da opção do menu*
    - O usuário poderá escolher entre as opções do menu, podendo digitar o valor(exemplo 1,2,3) ou o a descrição(exemplo:Cadastrar Cliente);
6. *Validaçao e Formatação do nome*
    - O nome não pode conter números e nem caracter especial.Depois de validado ele é formatado para que sua primeira letra seja maiuscula.
7. *Verificação do scanner nextLine()*
    - O nextLine() quando lido antes do nextInt,por exemplo,lê a tecla enter ao ínves do valor a ser digitado.Por isso,para todo uso do sc.nextLine()
antes é verificado ele leu um espaço ou um valor.Se ele leu um espaço é feito outra chamada do nextLine().
8. *Classe IniciarPrograma*
   - A classe IniciarPrograma inicia as listas de Clientes e Vendedores, para que seja possível fazer consultas e realizar compras sem a necessidade de cadastrar
manualmente.

    

