package br.com.roberto.hub_manager_app.domain.ports.in;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PaymentLinkInPort {

    Page<PaymentLinkModel> findAll(Pageable pageable, PaymentLinkFilter filter);
    Optional<PaymentLinkModel> findById(UUID id);
    PaymentLinkModel create(PaymentLinkModel paymentLink);
    PaymentLinkModel update(UUID id, PaymentLinkModel paymentLink);
    PaymentLinkModel disable(UUID id);
    void delete(UUID id);

}
