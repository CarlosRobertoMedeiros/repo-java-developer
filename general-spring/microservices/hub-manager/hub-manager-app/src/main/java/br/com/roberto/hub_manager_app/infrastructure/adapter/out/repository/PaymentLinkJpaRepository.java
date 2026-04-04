package br.com.roberto.hub_manager_app.infrastructure.adapter.out.repository;

import br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity.PaymentLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface PaymentLinkJpaRepository extends JpaRepository<PaymentLinkEntity, UUID> {
}
