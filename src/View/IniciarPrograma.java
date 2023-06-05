package View;

import Model.Cliente;
import Model.Vendedor;

public class IniciarPrograma {
    private Cliente cliente = new Cliente();
    private Vendedor vendedor = new Vendedor();


    public IniciarPrograma(Vendedor vendedor,Cliente cliente) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        iniciarBDs();
    }

    public void iniciarBDs(){
        cliente.adicionarCliente(new Cliente("João", "572.571.860-30", "joao@example.com", 25));
        cliente.adicionarCliente(new Cliente("Maria", "246.564.540-44", "maria@example.com", 30));
        cliente.adicionarCliente(new Cliente("Pedro", "309.930.100-08", "pedro@example.com", 35));
        cliente.adicionarCliente(new Cliente("Ana", "863.325.530-81", "ana@example.com", 40));
        cliente.adicionarCliente(new Cliente("Carlos", "201.479.790-05", "carlos@example.com", 45));

        vendedor.adicionarVendedor(new Vendedor("Eduardo", "478.539.980-58", "eduardo@example.com", 25));
        vendedor.adicionarVendedor(new Vendedor("Fernanda", "119.831.720-57", "fernanda@example.com", 30));
        vendedor.adicionarVendedor(new Vendedor("Ricardo", "427.038.280-55", "ricardo@example.com", 35));
        vendedor.adicionarVendedor(new Vendedor("Juliana", "796.287.960-20", "juliana@example.com", 40));
        vendedor.adicionarVendedor(new Vendedor("Mariana", "454.318.410-68", "mariana@example.com", 45));

    }
}
