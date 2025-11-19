package com.dealers.inventory.repository;

import com.dealers.inventory.model.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {
    @Query("select d from Dealer d where d.tenantId = :tenant")
    Page<Dealer> findByTenant(@Param("tenant") String tenant, Pageable p);
}
