package br.com.roberto.ms_conversor.application.port;

import br.com.roberto.ms_conversor.domain.model.Venda;

public interface ProcessarVendaPort {
    Venda processarVenda(Venda venda);
}
