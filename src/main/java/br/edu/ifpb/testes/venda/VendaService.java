package br.edu.ifpb.testes.venda;

import br.edu.ifpb.testes.Venda;
import br.edu.ifpb.testes.entrega.EntregaEfetuadaException;
import br.edu.ifpb.testes.entrega.EntregaIndisponivelException;

import java.util.List;

public interface VendaService {

    void registrar(Venda venda) throws EntregaIndisponivelException;
    void estornar(Venda venda) throws EntregaEfetuadaException;
    Venda recuperar(int codigo);
    List<Venda> listar();

}
