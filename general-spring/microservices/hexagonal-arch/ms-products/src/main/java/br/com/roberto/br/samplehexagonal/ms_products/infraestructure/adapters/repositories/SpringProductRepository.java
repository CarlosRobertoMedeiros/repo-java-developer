package br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.repositories;

import br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findBySku(String sku);
}
