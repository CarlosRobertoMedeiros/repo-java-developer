package br.com.roberto.hub_manager_app.infrastructure.adapter.out.adapter;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.application.ports.out.PaymentLinkOutPort;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity.PaymentLinkEntity;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.mapper.PaymentLinkModelEntityMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.repository.PaymentLinkJpaRepository;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.specification.SpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentLinkRepositoryAdapter implements PaymentLinkOutPort {

    private final PaymentLinkJpaRepository repository;
    private final PaymentLinkModelEntityMapper mapper;

    public PaymentLinkRepositoryAdapter(PaymentLinkModelEntityMapper mapper, PaymentLinkJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

//    @Override
//    public List<PaymentLinkModel> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(mapper::EntitytoModel)
//                .toList();
//    }

    @Override
    public Page<PaymentLinkModel> findAll(Pageable pageable, PaymentLinkFilter filter) {
        var spec = SpecificationBuilder.<PaymentLinkEntity>builder()
                .between("createdAt", filter.createdAtFrom(), filter.createdAtTo())
                .equal("isActive", filter.isActive())
                .likeIgnoreCase("description", filter.description())
                .build();

        return repository.findAll(spec, pageable)
                .map(mapper::EntitytoModel);
    }

    @Override
    public Optional<PaymentLinkModel> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::EntitytoModel);
    }

    @Override
    @Transactional
    public PaymentLinkModel save(PaymentLinkModel paymentLink) {

        if (paymentLink.getId() == null) {
            paymentLink.setPaymentUrl("https://payment.link/".concat(UUID.randomUUID().toString()));
        }else {
            paymentLink.setUpdatedAt(LocalDateTime.now());
        }
        var entity = mapper.ModeltoEntity(paymentLink);
        var saved = repository.save(entity);


        return mapper.EntitytoModel(saved);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("PaymentLink não encontrado para exclusão");
        }

        repository.deleteById(id);
    }
}
