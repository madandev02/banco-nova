
CREATE TABLE transfers (
 id SERIAL PRIMARY KEY,
 from_account_id BIGINT NOT NULL REFERENCES accounts(id),
 to_account_id BIGINT REFERENCES accounts(id),
 destination_bank VARCHAR(100) NOT NULL,
 destination_account_number VARCHAR(30) NOT NULL,
 amount NUMERIC(18,2) NOT NULL,
 created_at TIMESTAMP WITH TIME ZONE NOT NULL,
 description TEXT
);
