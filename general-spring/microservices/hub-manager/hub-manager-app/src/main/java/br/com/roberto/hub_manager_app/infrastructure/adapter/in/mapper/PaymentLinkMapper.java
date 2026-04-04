package br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request.PaymentLinkRequest;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response.PaymentLinkResponse;

import java.time.LocalDateTime;

public class PaymentLinkMapper {

    public static PaymentLinkModel toEntity(PaymentLinkRequest paymentLinkRequest){

        if (paymentLinkRequest == null) return null;

        PaymentLinkModel paymentLinkModel = new PaymentLinkModel();

        paymentLinkModel.setDescription(paymentLinkRequest.description());
        paymentLinkModel.setAmount(paymentLinkRequest.amount());
        paymentLinkModel.setExpirationDate(paymentLinkRequest.expirationDate());
        paymentLinkModel.setCreatedAt(LocalDateTime.now());
        paymentLinkModel.setStatus("CREATED");

        return paymentLinkModel;
    }

    public static PaymentLinkResponse toResponse(PaymentLinkModel paymentLinkModel){
        if (paymentLinkModel == null) return null;

        return new PaymentLinkResponse(
                paymentLinkModel.getId(),
                paymentLinkModel.getDescription(),
                paymentLinkModel.getAmount(),
                paymentLinkModel.getPaymentUrl(),
                paymentLinkModel.getStatus(),
                paymentLinkModel.getCreatedAt(),
                paymentLinkModel.getExpirationDate()
        );
    }

    public static void updateModel(PaymentLinkModel paymentLinkModel, PaymentLinkRequest paymentLinkRequest) {

        if (paymentLinkRequest.description() != null) {
            paymentLinkModel.setDescription(paymentLinkRequest.description());
        }

        if (paymentLinkRequest.amount() != null) {
            paymentLinkModel.setAmount(paymentLinkRequest.amount());
        }

        if (paymentLinkRequest.expirationDate() != null) {
            paymentLinkModel.setExpirationDate(paymentLinkRequest.expirationDate());
        }
    }
}

