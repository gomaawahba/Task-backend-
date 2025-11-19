package com.dealers.payment.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String t = TenantContext.get();
        return t == null ? "payment" : t;
    }

    @Override
    public boolean validateExistingCurrentSessions() { return false; }
}
