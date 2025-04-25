ALTER TABLE `role`
    ADD is_deleted BIT(1) NOT NULL DEFAULT false;

ALTER TABLE token
    ADD is_deleted BIT(1) NOT NULL DEFAULT false;

ALTER TABLE user
    ADD is_deleted BIT(1) NOT NULL DEFAULT false;