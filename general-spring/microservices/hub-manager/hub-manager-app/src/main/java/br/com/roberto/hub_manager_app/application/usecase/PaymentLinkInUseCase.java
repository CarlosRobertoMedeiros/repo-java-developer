package br.com.roberto.hub_manager_app.application.usecase;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.application.ports.out.PaymentLinkOutPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentLinkInUseCase implements PaymentLinkInPort {

    private final PaymentLinkOutPort paymentLinkOutPort;

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
        validatePaymentLink(paymentLink);

        paymentLink.setId(UUID.randomUUID());
        paymentLink.setActive(true);
        paymentLink.setCreatedAt(LocalDateTime.now());

        return paymentLinkOutPort.save(paymentLink);
    }

    @Override
    public PaymentLinkModel update(UUID id, PaymentLinkModel paymentLink) {
        validateId(id);
        validatePaymentLink(paymentLink);

        PaymentLinkModel existing = getOrThrow(id, "atualização");

        validateActive(existing);

        existing.setDescription(paymentLink.getDescription());
        existing.setAmount(paymentLink.getAmount());
        existing.setExpirationDate(paymentLink.getExpirationDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return paymentLinkOutPort.save(existing);
    }

    @Override
    public PaymentLinkModel disable(UUID id) {
        validateId(id);

        PaymentLinkModel existing = getOrThrow(id, "desativação");

        if (!Boolean.TRUE.equals(existing.getActive())) {
            throw new IllegalStateException("PaymentLink já está desativado");
        }

        existing.setActive(false);
        existing.setUpdatedAt(LocalDateTime.now());

        return paymentLinkOutPort.save(existing);
    }

    @Override
    public void delete(UUID id) {
        validateId(id);

        PaymentLinkModel existing = getOrThrow(id, "deleção");

        paymentLinkOutPort.delete(existing.getId());
    }

    private PaymentLinkModel getOrThrow(UUID id, String contexto) {
        return paymentLinkOutPort.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException(
                                String.format("PaymentLink não encontrado para %s: %s", contexto, id)
                        )
                );
    }

    private void validateActive(PaymentLinkModel paymentLink) {
        if (!Boolean.TRUE.equals(paymentLink.getActive())) {
            throw new IllegalStateException("PaymentLink desativado não pode ser alterado");
        }
    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
    }

    private void validatePaymentLink(PaymentLinkModel paymentLink) {
        if (paymentLink == null) {
            throw new IllegalArgumentException("PaymentLink não pode ser nulo");
        }

        validateAmount(paymentLink.getAmount());

        if (paymentLink.getDescription() == null || paymentLink.getDescription().isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }

        if (paymentLink.getExpirationDate() == null) {
            throw new IllegalArgumentException("Data de expiração é obrigatória");
        }

        if (paymentLink.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de expiração não pode estar no passado");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }
}