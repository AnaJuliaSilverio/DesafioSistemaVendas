package Repository;

import Model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    private List<Venda> vendas = new ArrayList<>();
    public List<Venda> getVendas() {
        if (vendas.isEmpty()) throw new NullPointerException("Lista de vendas vazia");
        return vendas;
    }

    public void adicionarVenda(Venda venda){
        vendas.add(venda);
    }
}
