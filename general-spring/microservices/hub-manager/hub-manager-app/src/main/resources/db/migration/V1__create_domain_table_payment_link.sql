-- Table TB_PAYMENT_LINK
CREATE TABLE IF NOT EXISTS TB_PAYMENT_LINK (
    id                                      UUID NOT NULL,
    description                             VARCHAR(100) NOT NULL,
    amount                                  DOUBLE PRECISION,
    expiration_date                         TIMESTAMP,
    created_at                              TIMESTAMP NOT NULL,
    updated_at                              TIMESTAMP,
    payment_url                             VARCHAR(255),
    status                                  VARCHAR(50),
    is_active BOOLEAN                       NOT NULL DEFAULT TRUE,
    CONSTRAINT tbl_payment_link_pkey        PRIMARY KEY (id)
);