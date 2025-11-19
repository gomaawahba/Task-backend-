package com.dealers.inventory.service;

import com.dealers.inventory.model.Vehicle;
import com.dealers.inventory.repository.VehicleRepository;
import com.dealers.inventory.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository repo;

    public Vehicle create(Vehicle v) {
        v.setTenantId(TenantContext.get());
        return repo.save(v);
    }

    public Vehicle get(UUID id) { return repo.findById(id).orElseThrow(); }

    public Page<Vehicle> search(String model, Vehicle.Status status, Double min, Double max, Pageable p) {
        return repo.search(TenantContext.get(), model, status, min, max, p);
    }

    public List<Vehicle> premium() {
        return repo.findPremiumVehicles(TenantContext.get());
    }
}
