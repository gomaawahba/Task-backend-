package com.dealers.inventory.service;

import com.dealers.inventory.model.Dealer;
import com.dealers.inventory.repository.DealerRepository;
import com.dealers.inventory.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealerService {
    @Autowired
    private  DealerRepository repo;

    public Dealer create(Dealer d) {
        d.setTenantId(TenantContext.get());
        return repo.save(d);
    }

    public Dealer get(UUID id) { return repo.findById(id).orElseThrow(); }

    public Page<Dealer> list(Pageable p) { return repo.findByTenant(TenantContext.get(), p); }

    public Map<String, Long> countBySubscription() {
        var all = repo.findByTenant(TenantContext.get(), Pageable.unpaged()).getContent();
        long basic = all.stream().filter(d -> d.getSubscriptionType() == Dealer.SubscriptionType.BASIC).count();
        long premium = all.stream().filter(d -> d.getSubscriptionType() == Dealer.SubscriptionType.PREMIUM).count();
        return Map.of("BASIC", basic, "PREMIUM", premium);
    }
}
