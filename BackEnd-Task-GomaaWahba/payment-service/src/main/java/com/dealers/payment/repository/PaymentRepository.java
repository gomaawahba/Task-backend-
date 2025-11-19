package com.dealers.payment.repository;

import com.dealers.payment.model.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentTransaction, UUID> {
    Optional<PaymentTransaction> findByRequestIdAndTenantId(String requestId, String tenantId);
}
