-- liquibase formatted sql
-- changeset etxo:001

INSERT INTO manager
(
    first_name,
    last_name,
    email,
    phone
)
VALUES
    ('Anton','Pupkin','pup@pupkovod.fu'),
    ('Nina','Rozhina','ninaro@mail.mu');

-- rollback DELETE FROM product WHERE first_name IN ('Anton', 'Nina');