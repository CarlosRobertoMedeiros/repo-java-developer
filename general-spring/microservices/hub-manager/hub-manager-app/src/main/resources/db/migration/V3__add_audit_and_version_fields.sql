-- Add version field for optimistic locking
ALTER TABLE TB_PAYMENT_LINK ADD COLUMN IF NOT EXISTS version BIGINT DEFAULT 0;

-- Add audit fields
ALTER TABLE TB_PAYMENT_LINK ADD COLUMN IF NOT EXISTS created_by VARCHAR(100);
ALTER TABLE TB_PAYMENT_LINK ADD COLUMN IF NOT EXISTS updated_by VARCHAR(100);

-- Add soft delete field
ALTER TABLE TB_PAYMENT_LINK ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP;

-- Add unique constraint on payment_url if not exists
ALTER TABLE TB_PAYMENT_LINK ADD CONSTRAINT IF NOT EXISTS uk_payment_link_payment_url UNIQUE (payment_url);

-- Update existing records to have version 0 if null
UPDATE TB_PAYMENT_LINK SET version = 0 WHERE version IS NULL;

-- Update existing records to have created_by as SYSTEM if null
UPDATE TB_PAYMENT_LINK SET created_by = 'SYSTEM' WHERE created_by IS NULL;

-- Add comments for new columns
COMMENT ON COLUMN TB_PAYMENT_LINK.version IS 'Version number for optimistic locking';
COMMENT ON COLUMN TB_PAYMENT_LINK.created_by IS 'User/system that created the record';
COMMENT ON COLUMN TB_PAYMENT_LINK.updated_by IS 'User/system that last updated the record';
COMMENT ON COLUMN TB_PAYMENT_LINK.deleted_at IS 'Timestamp when record was soft deleted (null if active)';
