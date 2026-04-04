package br.com.roberto.ms_conversor.infraestructure.adapter.input.rest;

import br.com.roberto.ms_conversor.application.port.ProcessarVendaPort;
import br.com.roberto.ms_conversor.domain.model.Venda;
import br.com.roberto.ms_conversor.infraestructure.adapter.input.request.JsonOrigemRequest;
import br.com.roberto.ms_conversor.infraestructure.adapter.output.response.JsonOrigemResponse;
import br.com.roberto.ms_conversor.infraestructure.mappers.MapperVenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final ProcessarVendaPort processarVendaPort;
    private final MapperVenda mapperVenda;

    public VendaController(ProcessarVendaPort processarVendaPort, MapperVenda mapperVenda) {
        this.processarVendaPort = processarVendaPort;
        this.mapperVenda = mapperVenda;
    }

    @PostMapping("/converter")
    public ResponseEntity<JsonOrigemResponse> converterVenda(@RequestBody JsonOrigemRequest jsonOrigemRequest) {

        Venda vendaDomain = mapperVenda.toDomain(jsonOrigemRequest);

        Venda vendaProcessada = processarVendaPort.processarVenda(vendaDomain);

        JsonOrigemResponse jsonOrigemResponse = mapperVenda.fromDomain(vendaProcessada);

        return ResponseEntity.ok(jsonOrigemResponse);
    }



}
