package br.edu.ifpb.testes.venda;

import br.edu.ifpb.testes.Venda;
import br.edu.ifpb.testes.entrega.EntregaEfetuadaException;
import br.edu.ifpb.testes.entrega.EntregaIndisponivelException;
import br.edu.ifpb.testes.entrega.EntregaService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class VendaServiceImplTest {

    @Mock
    private EntregaService entregaServiceMock;

    @Mock
    private VendaDAO vendaDAOMock;

    private VendaService vendaService;

    public VendaServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        vendaService = new VendaServiceImpl(entregaServiceMock, vendaDAOMock);
    }

    private Venda criarVendaFake() {
        Venda venda = new Venda();
        venda.setCodigo(1);
        venda.setFinalizada(false);
        venda.setReceptor("DIOGO DANTAS MOREIRA");
        venda.setIdReceptor("295.300.475-09");
        venda.setEmissor("TESTES DE SOFTWARE LTDA.");
        venda.setCnpjEmissor("55.723.748/0001-04");
        venda.setNumero(UUID.randomUUID().toString());
        venda.setCepEntrega("58900000");
        return venda;
    }

    @Test
    public void registrar() throws EntregaIndisponivelException {
        when(entregaServiceMock.isDisponivel("58900000")).thenReturn(true);
        vendaService.registrar(criarVendaFake());
        doNothing().when(vendaDAOMock).salvar(any(Venda.class));
        verify(entregaServiceMock, times(1)).isDisponivel("58900000");
    }

    @Test
    public void estornar() throws EntregaEfetuadaException {
        Venda vendaFake = criarVendaFake();
        doAnswer(new Answer<Venda>() {
            @Override
            public Venda answer(InvocationOnMock invocationOnMock) throws Throwable {
                return vendaFake;
            }
        }).when(vendaDAOMock).recuperar(1);

        Venda venda = new Venda();
        venda.setCodigo(1);

        vendaService.estornar(venda);
        Assert.assertFalse(venda.isFinalizada());
    }
}