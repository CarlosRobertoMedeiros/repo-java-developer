package br.com.roberto.hub_manager_app.infrastructure.adapter.out.generator;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;

/**
 * Port for generating payment URLs.
 * Different implementations can provide various URL generation strategies.
 */
public interface PaymentUrlGenerator {

    /**
     * Generates a payment URL for the given payment link.
     *
     * @param paymentLink the payment link model
     * @return the generated payment URL
     */
    String generatePaymentUrl(PaymentLinkModel paymentLink);

    /**
     * Validates if a payment URL is unique.
     *
     * @param paymentUrl the payment URL to validate
     * @return true if the URL is unique, false otherwise
     */
    boolean isPaymentUrlUnique(String paymentUrl);
}
