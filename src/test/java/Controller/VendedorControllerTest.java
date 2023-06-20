package Controller;

import Model.Cliente;
import Model.PessoaCadastrada;
import Model.Vendedor;
import Repository.VendedorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
@ExtendWith(MockitoExtension.class)
class VendedorControllerTest {
    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorController vendedorController =new VendedorController();;

    @Test
    void cadastrarClienteComCpfEmailRepetido(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(vendedorRepository.vendedorJaExiste(vendedor)).thenReturn(true);
        Assertions.assertThrows(PessoaCadastrada.class,()->vendedorController.cadastrarVendedor(vendedor));
        verify(vendedorRepository,times(1)).vendedorJaExiste(vendedor);
        verify(vendedorRepository,never()).adicionarVendedor(vendedor);
    }

    @Test
    void cadastrarClienteComCpfEmailNovo(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(vendedorRepository.vendedorJaExiste(vendedor)).thenReturn(false);
        vendedorController.cadastrarVendedor(vendedor);
        verify(vendedorRepository,times(1)).adicionarVendedor(vendedor);
    }
    @Test
    void procurarVendedorComEmailESenhaDesconhecido(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(vendedorRepository.procuraVendedorEmail(vendedor.getEmail(),vendedor.getSenha())).thenReturn(null);
        Assertions.assertNull(vendedorController.verificaCredenciais(vendedor.getEmail(), vendedor.getSenha()));
    }
    @Test
    void procurarVendedorComEmailESenhaConhecido(){
        Vendedor vendedor = new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!");
        when(vendedorRepository.procuraVendedorEmail(vendedor.getEmail(),vendedor.getSenha())).thenReturn(vendedor);
        Assertions.assertEquals(vendedor,vendedorController.verificaCredenciais(vendedor.getEmail(), vendedor.getSenha()));
    }
    @Test
    void listarClienteComListaNaoVazia(){
        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(new Vendedor("Ana","161.476.846-39","ana@alves.com",25,"Ju45!"));
        when(vendedorRepository.getVendedores()).thenReturn(vendedores);
        vendedorController.listarVendedores();
        verify(vendedorRepository,times(1)).getVendedores();
    }

}