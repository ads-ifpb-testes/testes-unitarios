package br.edu.ifpb.testes.venda;

import br.edu.ifpb.testes.Venda;
import br.edu.ifpb.testes.entrega.EntregaEfetuadaException;
import br.edu.ifpb.testes.entrega.EntregaIndisponivelException;
import br.edu.ifpb.testes.entrega.EntregaService;

import java.util.List;

public class VendaServiceImpl implements VendaService {

    private EntregaService servicoEntrega;
    private VendaDAO vendaDAO;

    public VendaServiceImpl(EntregaService servicoEntrega, VendaDAO vendaDAO) {
        this.servicoEntrega = servicoEntrega;
        this.vendaDAO = vendaDAO;
    }

    @Override
    public void registrar(Venda venda) throws EntregaIndisponivelException {
        if (!servicoEntrega.isDisponivel(venda.getCepEntrega())) {
            throw new EntregaIndisponivelException("Entrega não disponível para esse CEP");
        }
        venda.setFinalizada(true);
        vendaDAO.salvar(venda);
    }

    @Override
    public void estornar(Venda venda) throws EntregaEfetuadaException {
        Venda vendaAEstornar = vendaDAO.recuperar(venda.getCodigo());
        if(vendaAEstornar.isEntregue()) {
            throw new EntregaEfetuadaException("Entrega já efetuada, impossível estornar");
        }
        vendaAEstornar.setFinalizada(false);
        vendaDAO.salvar(venda);
    }

    @Override
    public Venda recuperar(int codigo) {
        return vendaDAO.recuperar(codigo);
    }

    @Override
    public List<Venda> listar() {
        return vendaDAO.listar();
    }
}
