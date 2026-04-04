
-- DEPENDENCY FOR UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO TB_PAYMENT_LINK (
    id,
    description,
    amount,
    expiration_date,
    created_at,
    updated_at,
    payment_url,
    status,
    is_active
)
SELECT
    gen_random_uuid() AS id,
    'PAYMENT ' || gs AS description,
    ROUND((RANDOM() * 1000)::numeric, 2) AS amount,
    NOW() + INTERVAL '48 hours' AS expiration_date,
    NOW() - (gs || ' hours')::interval AS created_at,
    NOW() AS updated_at,
    'https://payment.link/' || gs AS payment_url,
    CASE
    WHEN gs % 3 = 0 THEN 'EXPIRED'
    WHEN gs % 2 = 0 THEN 'PENDING'
    ELSE 'ACTIVE'
END AS status,
    CASE 
        WHEN gs % 5 = 0 THEN FALSE
        ELSE TRUE
END AS is_active
FROM generate_series(1, 200) AS gs;