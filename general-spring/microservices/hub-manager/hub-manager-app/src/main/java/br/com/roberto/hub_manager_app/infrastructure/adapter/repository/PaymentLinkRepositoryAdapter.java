package br.com.roberto.hub_manager_app.infrastructure.adapter.repository;

import br.com.roberto.hub_manager_app.domain.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.domain.ports.out.PaymentLinkOutPort;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.infrastructure.persistence.entity.PaymentLinkEntity;
import br.com.roberto.hub_manager_app.infrastructure.adapter.mapper.PaymentLinkModelEntityMapper;
import br.com.roberto.hub_manager_app.infrastructure.persistence.repository.PaymentLinkJpaRepository;
import br.com.roberto.hub_manager_app.infrastructure.persistence.specification.SpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    public Page<PaymentLinkModel> findAll(Pageable pageable, PaymentLinkFilter filter) {
        var spec = SpecificationBuilder.<PaymentLinkEntity>builder()
                .between("createdAt", filter.createdAtTo(), filter.createdAtFrom())
                .equal("isActive", filter.isActive())
                .likeIgnoreCase("description", filter.description())
                .build();

        return repository.findAll(spec, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<PaymentLinkModel> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public PaymentLinkModel save(PaymentLinkModel paymentLink) {

        if (paymentLink.getId() == null) {
            paymentLink.setPaymentUrl("https://payment.link/".concat(UUID.randomUUID().toString()));
        }else {
            paymentLink.setUpdatedAt(LocalDateTime.now());
        }
        var entity = mapper.toEntity(paymentLink);
        var saved = repository.save(entity);


        return mapper.toDomain(saved);
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
