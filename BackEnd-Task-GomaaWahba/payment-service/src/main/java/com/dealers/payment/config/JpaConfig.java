package com.dealers.payment.config;

import com.dealers.payment.tenant.MultiTenantConnectionProvider;
import com.dealers.payment.tenant.TenantSchemaResolver;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource ds) {
        return new MultiTenantConnectionProvider(ds);
    }

    @Bean
    public CurrentTenantIdentifierResolver tenantResolver() {
        return new TenantSchemaResolver();
    }
}
