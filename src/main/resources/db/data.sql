INSERT INTO fundraising_event (name, currency, account_balance)
VALUES
    ('Save the Children', 'EUR', 1500.00),
    ('Animal Rescue', 'USD', 250.00),
    ('Children’s Hospital Equipment Fund', 'PLN', 0.00);

INSERT INTO collection_box (assigned, empty, fundraising_event_id)
VALUES
    (TRUE, FALSE, 1),
    (FALSE, TRUE, NULL),
    (TRUE, TRUE, 2);

INSERT INTO collection_box_transaction (collection_box_id, currency, amount)
VALUES
    (1, 'EUR', 1110.00),
    (1, 'USD', 20.00),
    (1, 'PLN', 10.00);