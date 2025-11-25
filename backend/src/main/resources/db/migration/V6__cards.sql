
CREATE TABLE cards (
 id SERIAL PRIMARY KEY,
 user_id BIGINT NOT NULL REFERENCES users(id),
 type VARCHAR(20) NOT NULL,
 masked_number VARCHAR(30) NOT NULL,
 expiration_date DATE NOT NULL,
 cvv_hash VARCHAR(200) NOT NULL
);
