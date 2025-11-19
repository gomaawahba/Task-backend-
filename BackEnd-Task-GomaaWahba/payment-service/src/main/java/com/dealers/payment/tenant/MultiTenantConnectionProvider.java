package com.dealers.payment.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private final DataSource ds;

    public MultiTenantConnectionProvider(DataSource ds) { this.ds = ds; }

    @Override
    protected DataSource selectAnyDataSource() { return ds; }

    @Override
    protected DataSource selectDataSource(String tenantId) {
        try (Connection c = ds.getConnection(); Statement s = c.createStatement()) {
            s.execute("CREATE SCHEMA IF NOT EXISTS " + tenantId);
        } catch (SQLException e) { throw new RuntimeException(e); }
        return ds;
    }
}
