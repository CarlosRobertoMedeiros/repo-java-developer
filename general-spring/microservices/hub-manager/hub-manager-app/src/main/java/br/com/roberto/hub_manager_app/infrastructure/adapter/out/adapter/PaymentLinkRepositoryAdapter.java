package br.com.roberto.hub_manager_app.infrastructure.adapter.out.adapter;

import br.com.roberto.hub_manager_app.application.ports.out.PaymentLinkOutPort;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity.PaymentLinkEntity;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.out.repository.PaymentLinkJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentLinkRepositoryAdapter implements PaymentLinkOutPort {

    private final PaymentLinkJpaRepository repository;
    private final PaymentLinkMapper mapper;

    public PaymentLinkRepositoryAdapter(PaymentLinkMapper mapper, PaymentLinkJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<PaymentLinkModel> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Optional<PaymentLinkModel> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public PaymentLinkModel save(PaymentLinkModel paymentLink) {

        // Garante ID (caso venha nulo)
        if (paymentLink.getId() == null) {
            paymentLink.setId(UUID.randomUUID());
        }

        PaymentLinkEntity entity = mapper.toEntity(paymentLink);

        PaymentLinkEntity saved = repository.save(entity);

        return mapper.toModel(saved);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("PaymentLink não encontrado para exclusão");
        }

        repository.deleteById(id);
    }
}
