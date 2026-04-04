package br.com.roberto.hub_manager_app.application.ports.in;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentLinkInPort {

    List<PaymentLinkModel> findAll();
    Optional<PaymentLinkModel> findById(UUID id);
    PaymentLinkModel create(PaymentLinkModel paymentLink);
    PaymentLinkModel update(UUID id, PaymentLinkModel paymentLink);
    PaymentLinkModel disable(UUID id);
    void delete(UUID id);

}
