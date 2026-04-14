package br.com.roberto.hub_manager_app.infrastructure.adapter.mapper;

import br.com.roberto.hub_manager_app.domain.mapper.BaseDtoMapper;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
//import br.com.roberto.hub_manager_app.infrastructure.dto.request.PaymentLinkRequest;
//import br.com.roberto.hub_manager_app.infrastructure.dto.response.PaymentLinkResponse;
import br.com.roberto.model.PaymentLinkRequest;
import br.com.roberto.model.PaymentLinkResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentLinkMapper implements BaseDtoMapper<PaymentLinkModel, PaymentLinkRequest, PaymentLinkResponse> {
    @Override
    public PaymentLinkModel toDomain(PaymentLinkRequest paymentLinkRequest) {
        if (paymentLinkRequest == null) return null;

        PaymentLinkModel model = new PaymentLinkModel();
        model.setDescription(paymentLinkRequest.getDescription());
        model.setAmount(BigDecimal.valueOf(paymentLinkRequest.getAmount()));

        return model;
    }

    @Override
    public PaymentLinkResponse toResponse(PaymentLinkModel paymentLinkModel) {
        if (paymentLinkModel == null) return null;

        var response = new PaymentLinkResponse();

        response.setId(paymentLinkModel.getId());
        response.setDescription(paymentLinkModel.getDescription());
        response.setAmount(paymentLinkModel.getAmount());
        response.setIsActive (paymentLinkModel.getStatus() != null ? paymentLinkModel.getStatus().name() : "");
        response.setCreatedAt(paymentLinkModel.getCreatedAt());
        response.setUpdateAt(paymentLinkModel.getUpdatedAt());
        response.setExpirationDate(paymentLinkModel.getExpirationDate());
        response.setPaymentUrl(paymentLinkModel.getPaymentUrl());

        return response;
//        return new PaymentLinkResponse(
//                paymentLinkModel.getId(),
//                paymentLinkModel.getDescription(),
//                paymentLinkModel.getAmount(),
//                paymentLinkModel.getStatus() != null ? paymentLinkModel.getStatus().name() : "",
//                paymentLinkModel.getCreatedAt(),
//                paymentLinkModel.getUpdatedAt(),
//                paymentLinkModel.getExpirationDate(),
//                paymentLinkModel.getPaymentUrl()
//        );
    }

    @Override
    public void updateDomain(PaymentLinkModel paymentLinkModel, PaymentLinkRequest paymentLinkRequest) {
        if (paymentLinkRequest == null || paymentLinkModel == null) return;

        if (paymentLinkRequest.description() != null) {
            paymentLinkModel.setDescription(paymentLinkRequest.description());
        }

        if (paymentLinkRequest.amount() != null) {
            paymentLinkModel.setAmount(paymentLinkRequest.amount());
        }
    }

//    public static PaymentLinkModel toModel(PaymentLinkRequest paymentLinkRequest){
//
//        if (paymentLinkRequest == null) return null;
//
//        PaymentLinkModel model = new PaymentLinkModel();
//
//        model.setDescription(paymentLinkRequest.description());
//        model.setAmount(paymentLinkRequest.amount());
//
//        return model;
//    }
//
//    public static PaymentLinkResponse fromModel(PaymentLinkModel paymentLinkModel){
//        if (paymentLinkModel == null) return null;
//
//        return new PaymentLinkResponse(
//                paymentLinkModel.getId(),
//                paymentLinkModel.getDescription(),
//                paymentLinkModel.getAmount(),
//                paymentLinkModel.getPaymentUrl(),
//                paymentLinkModel.getStatus()!=null ? paymentLinkModel.getStatus().name() : "",
//                paymentLinkModel.getCreatedAt(),
//                paymentLinkModel.getUpdatedAt(),
//                paymentLinkModel.getExpirationDate()
//
//        );
//    }
//
//    public static void updateModel(PaymentLinkModel paymentLinkModel, PaymentLinkRequest paymentLinkRequest) {
//
//        if (paymentLinkRequest.description() != null) {
//            paymentLinkModel.setDescription(paymentLinkRequest.description());
//        }
//
//        if (paymentLinkRequest.amount() != null) {
//            paymentLinkModel.setAmount(paymentLinkRequest.amount());
//        }
//
//    }
}

