package com.dealers.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction extends BaseEntity {
    private java.util.UUID dealerId;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Method method;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String requestId;

    public enum Method { UPI, CARD, NET_BANKING }
    public enum Status { PENDING, SUCCESS, FAILED }

    public java.util.UUID getDealerId() { return dealerId; }
    public void setDealerId(java.util.UUID dealerId) { this.dealerId = dealerId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Method getMethod() { return method; }
    public void setMethod(Method method) { this.method = method; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
}
