package br.com.roberto.hub_manager_app.application.usecase;

import br.com.roberto.hub_manager_app.application.validator.PaymentLinkValidator;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.application.ports.out.PaymentLinkOutPort;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentLinkInUseCase implements PaymentLinkInPort {

    private final PaymentLinkOutPort paymentLinkOutPort;
    private static final int EXPIRATION_PAYMENT_LINK_IN_TWO_DAYS = 2;

    public PaymentLinkInUseCase(PaymentLinkOutPort paymentLinkOutPort) {
        this.paymentLinkOutPort = paymentLinkOutPort;
    }

    @Override
    public List<PaymentLinkModel> findAll() {
        return paymentLinkOutPort.findAll();
    }

    @Override
    public Optional<PaymentLinkModel> findById(UUID id) {
        validateId(id);
        return paymentLinkOutPort.findById(id);
    }

    @Override
    public PaymentLinkModel create(PaymentLinkModel paymentLink) {

        //Basic Data - Structural Validation
        PaymentLinkValidator.validate(paymentLink);

//        //Bussiness Rule Validator
//        validateExpirationDate(paymentLink);

        paymentLink.setStatus(PaymentLinkStatus.ACTIVE);
        paymentLink.setActive(true);
        paymentLink.setCreatedAt(LocalDateTime.now());
        paymentLink.setExpirationDate(paymentLink.getCreatedAt().plusDays(EXPIRATION_PAYMENT_LINK_IN_TWO_DAYS));

        var saved = paymentLinkOutPort.save(paymentLink);

        return saved;
    }

    @Override
    public PaymentLinkModel update(UUID id, PaymentLinkModel paymentLink) {

        validateId(id);
        //Basic Data - Structural Validation
        PaymentLinkValidator.validate(paymentLink);

        PaymentLinkModel existing = getOrThrow(id);

        validateUpdatable(existing);
        validateExpirationDate(paymentLink);

//        validatePaymentLink(paymentLink);
//
//        PaymentLinkModel existing = getOrThrow(id, "atualização");
//
//        validateActive(existing);

        existing.setDescription(paymentLink.getDescription());
        existing.setAmount(paymentLink.getAmount());
        existing.setExpirationDate(paymentLink.getExpirationDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return paymentLinkOutPort.save(existing);
    }

    @Override
    public PaymentLinkModel disable(UUID id) {
        validateId(id);

        PaymentLinkModel existing = getOrThrow(id);

        if (existing.getStatus() != PaymentLinkStatus.ACTIVE) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_STATUS_TRANSITION
            );
        }

        existing.setActive(false);
        existing.setStatus(PaymentLinkStatus.DISABLED);
        existing.setUpdatedAt(LocalDateTime.now());

        return paymentLinkOutPort.save(existing);
    }

    @Override
    public void delete(UUID id) {

        validateId(id);

        PaymentLinkModel existing = getOrThrow(id);

        paymentLinkOutPort.delete(existing.getId());
    }

    private PaymentLinkModel getOrThrow(UUID id) {
        return paymentLinkOutPort.findById(id)
                .orElseThrow(() ->
                        new PaymentLinkBusinessException(
                                PaymentLinkErrorCode.PAYMENT_LINK_NOT_FOUND
                        )
                );
    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_ID
            );
        }
    }

    private void validateUpdatable(PaymentLinkModel existing) {

        if (!Boolean.TRUE.equals(existing.getActive())) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.PAYMENT_LINK_INACTIVE
            );
        }

        if (existing.getStatus().equals(PaymentLinkStatus.EXPIRED)) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_STATUS_TRANSITION
            );
        }
    }

    private void validateExpirationDate(PaymentLinkModel paymentLink) {

        if (paymentLink.getExpirationDate() == null) {
            return; // TODO: opcional ???
        }

        if (paymentLink.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_EXPIRATION_DATE
            );
        }
    }
}