package com.br.testes.carga.infra.database.postgres.repository;

import com.br.testes.carga.infra.database.postgres.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductPostgresRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findByCodeIgnoreCase(final String code);
}
