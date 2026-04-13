package br.com.roberto.hub_manager_app.infrastructure.persistence.repository;

import br.com.roberto.hub_manager_app.infrastructure.persistence.entity.PaymentLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.UUID;

public interface PaymentLinkJpaRepository extends JpaRepository<PaymentLinkEntity, UUID>,
        JpaSpecificationExecutor<PaymentLinkEntity> {
}
