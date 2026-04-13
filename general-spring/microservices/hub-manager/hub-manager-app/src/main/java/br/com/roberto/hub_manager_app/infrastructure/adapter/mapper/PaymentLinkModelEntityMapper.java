package br.com.roberto.hub_manager_app.infrastructure.adapter.mapper;

import br.com.roberto.hub_manager_app.domain.mapper.BaseMapper;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.infrastructure.persistence.entity.PaymentLinkEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentLinkModelEntityMapper implements BaseMapper<PaymentLinkModel, PaymentLinkEntity> {
    @Override
    public PaymentLinkModel toDomain(PaymentLinkEntity entity) {
        if (entity == null) return null;

        PaymentLinkModel model = new PaymentLinkModel();
        model.setId(entity.getId());
        model.setDescription(entity.getDescription());
        model.setAmount(entity.getAmount());
        model.setCreatedAt(entity.getCreatedAt());
        model.setExpirationDate(entity.getExpirationDate());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setPaymentUrl(entity.getPaymentUrl());
        model.setStatus(entity.getStatus());
        model.setActive(entity.getActive());

        return model;
    }


    @Override
    public PaymentLinkEntity toEntity(PaymentLinkModel model) {
        if (model == null) return null;

        PaymentLinkEntity entity = new PaymentLinkEntity();
        entity.setId(model.getId());
        entity.setDescription(model.getDescription());
        entity.setAmount(model.getAmount());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setExpirationDate(model.getExpirationDate());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setPaymentUrl(model.getPaymentUrl());
        entity.setStatus(model.getStatus());
        entity.setActive(model.getActive());

        return entity;
    }

//    public PaymentLinkEntity ModeltoEntity(PaymentLinkModel model) {
//        if (model == null) return null;
//
//        PaymentLinkEntity entity = new PaymentLinkEntity();
//        entity.setId(model.getId());
//        entity.setDescription(model.getDescription());
//        entity.setAmount(model.getAmount());
//        entity.setCreatedAt(model.getCreatedAt());
//        entity.setExpirationDate(model.getExpirationDate());
//        entity.setUpdatedAt(model.getUpdatedAt());
//        entity.setPaymentUrl(model.getPaymentUrl());
//        entity.setStatus(model.getStatus());
//        entity.setActive(model.getActive());
//
//        return entity;
//    }
//
//    public PaymentLinkModel EntitytoModel(PaymentLinkEntity entity) {
//        if (entity == null) return null;
//
//        PaymentLinkModel model = new PaymentLinkModel();
//        model.setId(entity.getId());
//        model.setDescription(entity.getDescription());
//        model.setAmount(entity.getAmount());
//        model.setCreatedAt(entity.getCreatedAt());
//        model.setExpirationDate(entity.getExpirationDate());
//        model.setUpdatedAt(entity.getUpdatedAt());
//        model.setPaymentUrl(entity.getPaymentUrl());
//        model.setStatus(entity.getStatus());
//        model.setActive(entity.getActive());
//        return model;
//    }
}
