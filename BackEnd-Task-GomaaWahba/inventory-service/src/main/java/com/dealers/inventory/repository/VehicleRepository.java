package com.dealers.inventory.repository;

import com.dealers.inventory.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    @Query("select v from Vehicle v where v.tenantId = :tenant " +
           "and ( :model is null or v.model like %:model% ) " +
           "and ( :status is null or v.status = :status ) " +
           "and ( :min is null or v.price >= :min ) " +
           "and ( :max is null or v.price <= :max )")
    Page<Vehicle> search(@Param("tenant") String tenant,
                         @Param("model") String model,
                         @Param("status") Vehicle.Status status,
                         @Param("min") Double min,
                         @Param("max") Double max,
                         Pageable p);

    @Query("select v from Vehicle v join com.dealers.inventory.model.Dealer d on v.dealerId = d.id " +
           "where v.tenantId = :tenant and d.subscriptionType = 'PREMIUM'")
    List<Vehicle> findPremiumVehicles(@Param("tenant") String tenant);
}
