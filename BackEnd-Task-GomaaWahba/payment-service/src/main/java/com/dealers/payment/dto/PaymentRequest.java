package com.dealers.payment.dto;

import java.util.UUID;

public record PaymentRequest(UUID dealerId, Double amount, com.dealers.payment.model.PaymentTransaction.Method method) {}
