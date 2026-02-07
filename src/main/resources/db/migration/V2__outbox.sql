CREATE TABLE outbox (
                        id BIGSERIAL PRIMARY KEY,
                        aggregate_type TEXT NOT NULL,
                        aggregate_id UUID NOT NULL,
                        event_type TEXT NOT NULL,
                        payload JSONB NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT now(),
                        published BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE holds (
                       id UUID PRIMARY KEY,
                       account_id UUID NOT NULL,
                       amount BIGINT NOT NULL,
                       currency CHAR(3) NOT NULL,
                       status TEXT NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT now()
);