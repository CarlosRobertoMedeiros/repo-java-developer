package br.com.roberto.hub_manager_app.infrastructure.adapter.out.generator;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.repository.PaymentLinkJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * UUID-based implementation of PaymentUrlGenerator.
 * Generates payment URLs using UUIDs as identifiers.
 */
@Slf4j
@Component
public class UuidBasedPaymentUrlGenerator implements PaymentUrlGenerator {

    private static final String BASE_URL = "https://payment.link/";
    private static final int MAX_RETRIES = 3;

    private final PaymentLinkJpaRepository repository;

    public UuidBasedPaymentUrlGenerator(PaymentLinkJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public String generatePaymentUrl(PaymentLinkModel paymentLink) {
        log.debug("Generating payment URL for payment link id: {}", paymentLink.getId());

        String paymentUrl;
        int retries = 0;

        // Retry logic in case of UUID collision (extremely unlikely)
        do {
            paymentUrl = BASE_URL + UUID.randomUUID();
            retries++;
        } while (!isPaymentUrlUnique(paymentUrl) && retries < MAX_RETRIES);

        if (retries >= MAX_RETRIES && !isPaymentUrlUnique(paymentUrl)) {
            log.error("Failed to generate unique payment URL after {} retries", MAX_RETRIES);
            throw new RuntimeException("Unable to generate unique payment URL");
        }

        log.debug("Generated unique payment URL: {} (retries: {})", paymentUrl, retries - 1);
        return paymentUrl;
    }

    @Override
    public boolean isPaymentUrlUnique(String paymentUrl) {
        log.debug("Checking uniqueness of payment URL: {}", paymentUrl);
        // Using the JPA repository to check if the URL already exists
        // This is a simple approach; in production, you might use a database unique constraint
        boolean exists = repository.findAll().stream()
                .anyMatch(entity -> entity.getPaymentUrl() != null && entity.getPaymentUrl().equals(paymentUrl));

        log.debug("Payment URL uniqueness check result: unique={}", !exists);
        return !exists;
    }
}
