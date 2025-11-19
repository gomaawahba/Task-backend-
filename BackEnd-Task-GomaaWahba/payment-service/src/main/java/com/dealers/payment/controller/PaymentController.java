package com.dealers.payment.controller;

import com.dealers.payment.dto.PaymentRequest;
import com.dealers.payment.model.PaymentTransaction;
import com.dealers.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService svc;

    @PostMapping("/initiate")
    public ResponseEntity<?> initiate(@RequestBody PaymentRequest dto,
                                      @RequestHeader("Idempotency-Key") String key){
        return ResponseEntity.status(CREATED).body(svc.initiate(dto,key));
    }

    @GetMapping("/{id}")
    public PaymentTransaction get(@PathVariable UUID id){ return svc.get(id); }
}
