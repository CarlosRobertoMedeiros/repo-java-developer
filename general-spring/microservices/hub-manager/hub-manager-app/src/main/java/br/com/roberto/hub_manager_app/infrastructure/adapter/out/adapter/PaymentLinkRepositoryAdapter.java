package br.com.roberto.hub_manager_app.infrastructure.adapter.out.adapter;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.application.ports.out.PaymentLinkOutPort;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity.PaymentLinkEntity;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.generator.PaymentUrlGenerator;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.mapper.PaymentLinkModelEntityMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.repository.PaymentLinkJpaRepository;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.specification.SpecificationBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PaymentLinkRepositoryAdapter implements PaymentLinkOutPort {

    private final PaymentLinkJpaRepository repository;
    private final PaymentLinkModelEntityMapper mapper;
    private final PaymentUrlGenerator paymentUrlGenerator;

    public PaymentLinkRepositoryAdapter(
            PaymentLinkModelEntityMapper mapper,
            PaymentLinkJpaRepository repository,
            PaymentUrlGenerator paymentUrlGenerator) {
        this.mapper = mapper;
        this.repository = repository;
        this.paymentUrlGenerator = paymentUrlGenerator;
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
        log.debug("Querying payment links from database with filter: {}, pageable: {}", filter, pageable);

        var spec = SpecificationBuilder.<PaymentLinkEntity>builder()
                .between("createdAt", filter.createdAtFrom(), filter.createdAtTo())
                .equal("isActive", filter.isActive())
                .likeIgnoreCase("description", filter.description())
                .build();

        var result = repository.findAll(spec, pageable)
                .map(mapper::EntitytoModel);

        log.info("Database query returned {} payment links", result.getTotalElements());
        return result;
    }

    @Override
    public Optional<PaymentLinkModel> findById(UUID id) {
        log.debug("Querying payment link from database with id: {}", id);
        var result = repository.findById(id)
                .map(mapper::EntitytoModel);

        if (result.isPresent()) {
            log.debug("Payment link found in database with id: {}", id);
        } else {
            log.debug("Payment link not found in database with id: {}", id);
        }

        return result;
    }

    @Override
    @Transactional
    public PaymentLinkModel save(PaymentLinkModel paymentLink) {
        log.debug("Saving payment link to database - id: {}, isNew: {}", paymentLink.getId(), paymentLink.getId() == null);

        if (paymentLink.getId() == null) {
            // Generate unique payment URL for new payment links
            String paymentUrl = paymentUrlGenerator.generatePaymentUrl(paymentLink);
            paymentLink.setPaymentUrl(paymentUrl);
            log.debug("Generated unique payment URL: {}", paymentUrl);
        } else {
            paymentLink.setUpdatedAt(LocalDateTime.now());
        }

        var entity = mapper.ModeltoEntity(paymentLink);
        var saved = repository.save(entity);
        var result = mapper.EntitytoModel(saved);

        log.info("Payment link saved successfully to database - id: {}", result.getId());
        return result;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.debug("Attempting to delete payment link from database - id: {}", id);

        if (!repository.existsById(id)) {
            log.warn("Cannot delete payment link - not found in database - id: {}", id);
            throw new PaymentLinkBusinessException(PaymentLinkErrorCode.PAYMENT_LINK_NOT_FOUND);
        }

        repository.deleteById(id);
        log.info("Payment link deleted successfully from database - id: {}", id);
    }
}
