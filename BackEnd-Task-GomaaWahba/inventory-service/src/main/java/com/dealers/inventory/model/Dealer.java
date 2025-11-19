package com.dealers.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "dealers")
public class Dealer extends BaseEntity {
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    public enum SubscriptionType { BASIC, PREMIUM }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public SubscriptionType getSubscriptionType() { return subscriptionType; }
    public void setSubscriptionType(SubscriptionType subscriptionType) { this.subscriptionType = subscriptionType; }
}
