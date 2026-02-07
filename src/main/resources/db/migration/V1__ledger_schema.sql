CREATE TABLE accounts (
                          id UUID PRIMARY KEY,
                          currency CHAR(3) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT now()
);


CREATE TABLE transactions (
                              id UUID PRIMARY KEY,
                              idempotency_key TEXT UNIQUE NOT NULL,
                              status TEXT NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT now()
);


CREATE TABLE entries (
                         id BIGSERIAL PRIMARY KEY,
                         transaction_id UUID NOT NULL,
                         account_id UUID NOT NULL,
                         amount BIGINT NOT NULL,
                         currency CHAR(3) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT now(),


                         CONSTRAINT fk_tx FOREIGN KEY (transaction_id) REFERENCES transactions(id),
                         CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts(id)
);