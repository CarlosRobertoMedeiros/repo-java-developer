-- DEPENDENCY FOR UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- INSERT MOCK DATA (200 RECORDS)
INSERT INTO TB_PAYMENT_LINK (
    id,
    description,
    amount,
    expiration_date,
    created_at,
    updated_at,
    payment_url,
    payment_link_status,
    is_active
)
SELECT
    gen_random_uuid() AS id,
    'PAYMENT ' || gs AS description,
    ROUND((RANDOM() * 1000 + 10)::numeric, 2)::double precision AS amount,
    -- Expiration logic aligned with status
    CASE
        WHEN gs % 4 = 0 THEN NOW() - (gs || ' hours')::interval -- expired
        ELSE NOW() + ((gs % 72) || ' hours')::interval          -- future
    END AS expiration_date,
    NOW() - ((gs % 240) || ' hours')::interval AS created_at,
    NOW() AS updated_at,
    'https://payment.link/' || gs AS payment_url,
    -- Better distribution of statuses
    CASE
        WHEN gs % 4 = 0 THEN 'EXPIRED'
        WHEN gs % 4 = 1 THEN 'PENDING'
        WHEN gs % 4 = 2 THEN 'ACTIVE'
        ELSE 'DISABLED'
    END::STATUS_TYPE AS status,
    -- Disabled records aligned with status
    CASE
        WHEN gs % 4 = 3 THEN FALSE
        ELSE TRUE
    END AS is_active

FROM generate_series(1, 200) AS gs;