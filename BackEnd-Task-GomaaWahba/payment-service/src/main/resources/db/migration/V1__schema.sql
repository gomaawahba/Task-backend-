CREATE TABLE payment_transactions (
  id UUID PRIMARY KEY,
  tenant_id VARCHAR(50) NOT NULL,
  dealer_id UUID,
  amount DOUBLE,
  method VARCHAR(20),
  status VARCHAR(20),
  request_id VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
