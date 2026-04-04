package br.com.roberto.hub_manager_app.infrastructure.adapter.out.mapper;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity.PaymentLinkEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentLinkMapper {

    public PaymentLinkEntity toEntity(PaymentLinkModel model) {
        if (model == null) return null;

        PaymentLinkEntity entity = new PaymentLinkEntity();
        entity.setId(model.getId());
        entity.setDescription(model.getDescription());
        entity.setAmount(model.getAmount());
        entity.setActive(model.getActive());
        return entity;
    }

    public PaymentLinkModel toModel(PaymentLinkEntity entity) {
        if (entity == null) return null;

        PaymentLinkModel model = new PaymentLinkModel();
        model.setId(entity.getId());
        model.setDescription(entity.getDescription());
        model.setAmount(entity.getAmount());
        model.setActive(entity.getActive());
        return model;
    }
}
