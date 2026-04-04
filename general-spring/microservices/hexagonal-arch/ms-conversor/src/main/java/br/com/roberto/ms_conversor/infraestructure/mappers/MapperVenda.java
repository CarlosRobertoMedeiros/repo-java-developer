package br.com.roberto.ms_conversor.infraestructure.mappers;

import br.com.roberto.ms_conversor.domain.model.Venda;
import br.com.roberto.ms_conversor.infraestructure.adapter.input.request.JsonOrigemRequest;
import br.com.roberto.ms_conversor.infraestructure.adapter.output.response.BillingOrigemResponse;
import br.com.roberto.ms_conversor.infraestructure.adapter.output.response.ItemOrigemResponse;
import br.com.roberto.ms_conversor.infraestructure.adapter.output.response.JsonOrigemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperVenda {
    public Venda toDomain(JsonOrigemRequest request) {
//        List<ItemVenda> domainItems = request.billingRequest().items().stream()
//                .map(item -> new ItemVenda(
//                        new BigDecimal(item.quantity()),
//                        BigDecimal.valueOf(item.unitAmount()),
//                        item.description()
//                ))
//                .collect(Collectors.toList());
//
//        return new Venda(
//                request.date(),
//                request.type(),
//                request.origin(),
//                request.status(),
//                domainItems
//        );
        return request.toDomain();
    }

    public JsonOrigemResponse fromDomain(Venda domain) {
        List<ItemOrigemResponse> destinoItems = domain.items().stream()
                .map(item -> new ItemOrigemResponse(
                        item.quantity().toString(),
                        item.unitAmount().doubleValue(),
                        item.description()
                ))
                .toList();

        return new JsonOrigemResponse(
                domain.date(),
                domain.type(),
                domain.origin(),
                domain.status(),
                new BillingOrigemResponse(destinoItems)
        );
    }
}
