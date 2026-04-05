package br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request.PaymentLinkRequest;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response.PaymentLinkResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentLinkMapper {

    public static PaymentLinkModel toModel(PaymentLinkRequest paymentLinkRequest){

        if (paymentLinkRequest == null) return null;

        PaymentLinkModel model = new PaymentLinkModel();

        model.setDescription(paymentLinkRequest.description());
        model.setAmount(paymentLinkRequest.amount());

//        // status opcional (normalmente controlado pelo UseCase)
//        if (paymentLinkRequest.s() != null && !paymentLinkRequest.getStatus().isBlank()) {
//            model.setStatus(PaymentLinkStatus.from(paymentLinkRequest.getStatus()));
//        }

        return model;
    }

    public static PaymentLinkResponse fromModel(PaymentLinkModel paymentLinkModel){
        if (paymentLinkModel == null) return null;

        return new PaymentLinkResponse(
                paymentLinkModel.getId(),
                paymentLinkModel.getDescription(),
                paymentLinkModel.getAmount(),
                paymentLinkModel.getPaymentUrl(),
                paymentLinkModel.getStatus()!=null ? paymentLinkModel.getStatus().name() : "",
                paymentLinkModel.getCreatedAt(),
                paymentLinkModel.getUpdatedAt(),
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

//        if (paymentLinkRequest.expirationDate() != null) {
//            paymentLinkModel.setExpirationDate(paymentLinkRequest.expirationDate());
//        }
    }
}

