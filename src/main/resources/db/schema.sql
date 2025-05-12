CREATE TABLE fundraising_event (
                                   event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(255) NOT NULL,
                                   currency VARCHAR(3) NOT NULL,
                                   account_balance DECIMAL(12,2) NOT NULL DEFAULT 0.00
);

CREATE TABLE collection_box (
                                box_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                assigned BOOLEAN NOT NULL DEFAULT FALSE,
                                empty BOOLEAN NOT NULL DEFAULT TRUE,
                                fundraising_event_id BIGINT
);

CREATE TABLE collection_box_transaction (
                                            transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            collection_box_id BIGINT NOT NULL,
                                            currency VARCHAR(3) NOT NULL,
                                            amount DECIMAL(12,2) NOT NULL
);

ALTER TABLE collection_box ADD CONSTRAINT collection_box_to_fundraising_event
        FOREIGN KEY (fundraising_event_id)
            REFERENCES fundraising_event(event_id);

ALTER TABLE collection_box_transaction ADD CONSTRAINT transaction_to_collection_box
        FOREIGN KEY (collection_box_id)
            REFERENCES collection_box(box_id);
