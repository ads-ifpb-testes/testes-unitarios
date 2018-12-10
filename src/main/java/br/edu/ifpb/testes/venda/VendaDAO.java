package br.edu.ifpb.testes.venda;

import br.edu.ifpb.testes.Venda;

import java.util.List;

public interface VendaDAO {

    void salvar(Venda venda);

    Venda recuperar(int codigo);

    List<Venda> listar();
}
