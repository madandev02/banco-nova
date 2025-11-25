
CREATE TABLE movements (
 id SERIAL PRIMARY KEY,
 account_id BIGINT NOT NULL REFERENCES accounts(id),
 type VARCHAR(30) NOT NULL,
 amount NUMERIC(18,2) NOT NULL,
 created_at TIMESTAMP WITH TIME ZONE NOT NULL,
 description TEXT
);
