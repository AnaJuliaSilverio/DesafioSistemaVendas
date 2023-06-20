package View;

import Model.Cliente;
import Model.Vendedor;
import Repository.ClienteRepository;
import Repository.VendedorRepository;

public class IniciarPrograma {
    private ClienteRepository clienteRepository;
    private VendedorRepository vendedorRepository;


    public IniciarPrograma(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public IniciarPrograma(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void iniciarBDCliente(){
        clienteRepository.adicionarCliente(new Cliente("João", "572.571.860-30", "joao@example.com", 25,"A#b3C"));
        clienteRepository.adicionarCliente(new Cliente("Maria", "246.564.540-44", "maria@example.com", 30,"X$y9Z"));
        clienteRepository.adicionarCliente(new Cliente("Pedro", "309.930.100-08", "pedro@example.com", 35,"M@p5Q"));
        clienteRepository.adicionarCliente(new Cliente("Ana", "863.325.530-81", "ana@example.com", 40,"L!k2J"));
        clienteRepository.adicionarCliente(new Cliente("Carlos", "201.479.790-05", "carlos@example.com", 45,"R#s8T"));
    }
    public void iniciarBDVendedor(){
        vendedorRepository.adicionarVendedor(new Vendedor("Eduardo", "478.539.980-58", "eduardo@example.com", 25,"G!h6F"));
        vendedorRepository.adicionarVendedor(new Vendedor("Fernanda", "119.831.720-57", "fernanda@example.com", 30,"E@i4D"));
        vendedorRepository.adicionarVendedor(new Vendedor("Ricardo", "427.038.280-55", "ricardo@example.com", 35,"B%v7N"));
        vendedorRepository.adicionarVendedor(new Vendedor("Juliana", "796.287.960-20", "juliana@example.com", 40,"K$m1I"));
        vendedorRepository.adicionarVendedor(new Vendedor("Mariana", "454.318.410-68", "mariana@example.com", 45,"T&u0S"));
    }
}
