package br.com.roberto.hub_manager_app.application.ports.out;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentLinkOutPort {

    List<PaymentLinkModel> findAll();
    Optional<PaymentLinkModel> findById(UUID id);
    PaymentLinkModel save(PaymentLinkModel paymentLink);
    void delete(UUID id);

}
