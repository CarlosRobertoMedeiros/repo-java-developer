package br.com.roberto.hub_manager_app.domain.ports.out;

import br.com.roberto.hub_manager_app.domain.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PaymentLinkOutPort {

    Page<PaymentLinkModel> findAll(Pageable pageable, PaymentLinkFilter filter);
    Optional<PaymentLinkModel> findById(UUID id);
    PaymentLinkModel save(PaymentLinkModel paymentLink);
    void delete(UUID id);

}
