package br.edu.ifpb.testes.venda;

import br.edu.ifpb.testes.Venda;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VendaDAOMemoria implements VendaDAO {

    private List<Venda> vendas = new LinkedList<>();

    @Override
    public void salvar(Venda venda) {
        this.vendas.add(venda);
    }

    @Override
    public Venda recuperar(int codigo) {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        return vendas.get(vendas.lastIndexOf(venda));
    }

    @Override
    public List<Venda> listar() {
        return Collections.unmodifiableList(vendas);
    }
}
