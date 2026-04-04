package br.com.roberto.ms_conversor.application.service;

import br.com.roberto.ms_conversor.application.port.ProcessarVendaPort;
import br.com.roberto.ms_conversor.domain.model.Venda;
import org.springframework.stereotype.Service;

@Service
public class ProcessarVendaService implements ProcessarVendaPort {

    @Override
    public Venda processarVenda(Venda venda) {

        //Logica de Negocio

        if ("SALE".equals(venda.type())){
            return new Venda(
                    venda.date(),
                    venda.type(),
                    "ORIGEM_DESTINO", // <-- A Conversão de um campo de Domínio ocorre aqui
                    venda.status(),
                    venda.items()
            );
        }
        return venda;
    }
}
