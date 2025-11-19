CREATE TABLE dealers (
  id UUID PRIMARY KEY,
  tenant_id VARCHAR(50) NOT NULL,
  name  VARCHAR(100),
  email VARCHAR(100),
  subscription_type VARCHAR(10)
);

CREATE TABLE vehicles (
  id UUID PRIMARY KEY,
  tenant_id VARCHAR(50) NOT NULL,
  dealer_id UUID,
  model VARCHAR(100),
  price DOUBLE,
  status VARCHAR(10)
);
