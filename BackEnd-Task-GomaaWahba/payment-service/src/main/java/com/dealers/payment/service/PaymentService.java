package com.dealers.payment.service;

import com.dealers.payment.dto.PaymentRequest;
import com.dealers.payment.model.PaymentTransaction;
import com.dealers.payment.repository.PaymentRepository;
import com.dealers.payment.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repo;
    private final reactor.core.scheduler.Scheduler scheduler = Schedulers.newBoundedElastic(5, Integer.MAX_VALUE, "pay", 60, true);

    @Transactional
    public PaymentTransaction initiate(PaymentRequest dto, String idempKey){
        String tenant = TenantContext.get();
        return repo.findByRequestIdAndTenantId(idempKey, tenant)
                .orElseGet(() -> {
                    PaymentTransaction p = new PaymentTransaction();
                    p.setTenantId(tenant);
                    p.setDealerId(dto.dealerId());
                    p.setAmount(dto.amount());
                    p.setMethod(dto.method());
                    p.setRequestId(idempKey);
                    repo.save(p);

                    Mono.delay(Duration.ofSeconds(5))
                        .publishOn(scheduler)
                        .subscribe(i -> succeed(p.getId()));

                    return p;
                });
    }

    @Transactional
    public void succeed(UUID id){
        repo.findById(id).ifPresent(tx -> {
            tx.setStatus(PaymentTransaction.Status.SUCCESS);
            repo.save(tx);
        });
    }

    public PaymentTransaction get(UUID id){ return repo.findById(id).orElseThrow(); }
}
