-- CREATE Enum STATUS_TYPE
CREATE TYPE STATUS_TYPE AS ENUM (
    'EXPIRED',
    'PENDING',
    'ACTIVE',
    'DISABLED'
);

-- CREATE Table TB_PAYMENT_LINK
CREATE TABLE IF NOT EXISTS TB_PAYMENT_LINK (
    id                                      UUID NOT NULL,
    description                             VARCHAR(100) NOT NULL,
    amount                                  DOUBLE PRECISION,
    expiration_date                         TIMESTAMP,
    created_at                              TIMESTAMP NOT NULL,
    updated_at                              TIMESTAMP,
    payment_url                             VARCHAR(255),
    payment_link_status                     STATUS_TYPE NOT NULL DEFAULT 'ACTIVE',
    is_active                               BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT tb_payment_link_pkey         PRIMARY KEY (id)
);

-- CREATE INDEX
CREATE INDEX idx_payment_link_status ON TB_PAYMENT_LINK(status);
CREATE INDEX idx_payment_link_created_at ON TB_PAYMENT_LINK(created_at);

-- CREATE COMMENTS
COMMENT ON TABLE  TB_PAYMENT_LINK IS 'Table responsible for generating payment links';
COMMENT ON CONSTRAINT tb_payment_link_pkey ON TB_PAYMENT_LINK IS 'Primary key for payment link table';
COMMENT ON COLUMN TB_PAYMENT_LINK.id IS 'Unique identifier (UUID)';
COMMENT ON COLUMN TB_PAYMENT_LINK.description IS 'Payment description';
COMMENT ON COLUMN TB_PAYMENT_LINK.amount IS 'Payment amount';
COMMENT ON COLUMN TB_PAYMENT_LINK.expiration_date IS 'Expiration date of the link';
COMMENT ON COLUMN TB_PAYMENT_LINK.created_at IS 'Creation timestamp';
COMMENT ON COLUMN TB_PAYMENT_LINK.updated_at IS 'Last update timestamp';
COMMENT ON COLUMN TB_PAYMENT_LINK.payment_url IS 'Payment access URL';
COMMENT ON COLUMN TB_PAYMENT_LINK.status IS 'Current status (EXPIRED, PENDING, ACTIVE, DISABLED)';
COMMENT ON COLUMN TB_PAYMENT_LINK.is_active IS 'Indicates if the record is active';