package br.com.roberto.hub_manager_app.application.usecase;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.application.validator.PaymentLinkValidator;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.application.ports.out.PaymentLinkOutPort;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PaymentLinkInUseCase implements PaymentLinkInPort {

    private final PaymentLinkOutPort paymentLinkOutPort;
    private static final int EXPIRATION_PAYMENT_LINK_IN_TWO_DAYS = 2;

    public PaymentLinkInUseCase(PaymentLinkOutPort paymentLinkOutPort) {
        this.paymentLinkOutPort = paymentLinkOutPort;
    }

//    @Override
//    public List<PaymentLinkModel> findAll() {
//        return paymentLinkOutPort.findAll();
//    }


    @Override
    public Page<PaymentLinkModel> findAll(Pageable pageable, PaymentLinkFilter filter) {
        log.debug("Fetching all payment links with filter: {}, pageable: {}", filter, pageable);
        var result = paymentLinkOutPort.findAll(pageable, filter);
        log.info("Found {} payment links", result.getTotalElements());
        return result;
    }

    @Override
    public Optional<PaymentLinkModel> findById(UUID id) {
        log.debug("Fetching payment link by id: {}", id);
        validateId(id);
        return paymentLinkOutPort.findById(id);
    }

    @Override
    public PaymentLinkModel create(PaymentLinkModel paymentLink) {
        log.info("Creating payment link with description: {}", paymentLink.getDescription());

        //Basic Data - Structural Validation
        PaymentLinkValidator.validate(paymentLink);

//        //Bussiness Rule Validator
//        validateExpirationDate(paymentLink);

        paymentLink.setStatus(PaymentLinkStatus.ACTIVE);
        paymentLink.setActive(true);
        paymentLink.setCreatedAt(LocalDateTime.now());
        paymentLink.setExpirationDate(paymentLink.getCreatedAt().plusDays(EXPIRATION_PAYMENT_LINK_IN_TWO_DAYS));

        var saved = paymentLinkOutPort.save(paymentLink);
        log.info("Payment link created successfully with id: {}", saved.getId());

        return saved;
    }

    @Override
    public PaymentLinkModel update(UUID id, PaymentLinkModel paymentLink) {
        log.info("Updating payment link with id: {}", id);

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

        var updated = paymentLinkOutPort.save(existing);
        log.info("Payment link updated successfully with id: {}", id);
        return updated;
    }

    @Override
    public PaymentLinkModel disable(UUID id) {
        log.info("Disabling payment link with id: {}", id);
        validateId(id);

        PaymentLinkModel existing = getOrThrow(id);

        if (existing.getStatus() != PaymentLinkStatus.ACTIVE) {
            log.warn("Cannot disable payment link with id: {} - invalid status: {}", id, existing.getStatus());
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_STATUS_TRANSITION
            );
        }

        existing.setActive(false);
        existing.setStatus(PaymentLinkStatus.DISABLED);
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setExpirationDate(LocalDateTime.now());

        var disabled = paymentLinkOutPort.save(existing);
        log.info("Payment link disabled successfully with id: {}", id);
        return disabled;
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting payment link with id: {}", id);

        validateId(id);

        PaymentLinkModel existing = getOrThrow(id);

        paymentLinkOutPort.delete(existing.getId());
        log.info("Payment link deleted successfully with id: {}", id);
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
            log.warn("Invalid ID validation failed - id is null");
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_ID
            );
        }
    }

    private void validateUpdatable(PaymentLinkModel existing) {

        if (!Boolean.TRUE.equals(existing.getActive())) {
            log.warn("Payment link is not active - id: {}", existing.getId());
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.PAYMENT_LINK_INACTIVE
            );
        }

        if (existing.getStatus().equals(PaymentLinkStatus.EXPIRED)) {
            log.warn("Payment link has expired - id: {}", existing.getId());
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
            log.warn("Expiration date validation failed - date: {}", paymentLink.getExpirationDate());
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_EXPIRATION_DATE
            );
        }
    }
}