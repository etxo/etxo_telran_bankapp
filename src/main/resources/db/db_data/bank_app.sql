CREATE TABLE `manager` (
  `id` int PRIMARY KEY,
  `first_name` varchar(50),
  `last_name` varchar(50),
  `email` varchar(50) UNIQUE,
  `phone` varchar(15),
  `status` int(1),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `client` (
  `id` bigint PRIMARY KEY,
  `status` varchar(15),
  `first_name` varchar(50),
  `last_name` varchar(50),
  `email` varchar(60),
  `address_id` bigint,
  `phone` varchar(20),
  `created_at` timestamp,
  `updated_at` timestamp,
  `manager_id` int
);

CREATE TABLE `account` (
  `id` bigint PRIMARY KEY,
  `client_id` bigint,
  `iban` varchar(45),
  `bic` varchar(9),
  `account_type` int(1),
  `status` varchar(15),
  `balance` decimal(12, 2),
  `currency_code` varchar(2),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `transaction` (
  `id` bigint PRIMARY KEY,
  `sender_id` bigint,
  `receiver_id` bigint,
  `amount` decimal(9, 2),
  `description` varchar(255),
  `executed_at` timestamp
);

CREATE TABLE `address` (
  `id` bigint PRIMARY KEY,
  `postal_code` varchar(7),
  `city` varchar(45),
  `street` varchar(255),
  `house_nr` varchar(9),
  `country_code` varchar(5),
  `created_at` timestamp
);

ALTER TABLE `client` ADD FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`);

ALTER TABLE `account` ADD FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

ALTER TABLE `transaction` ADD FOREIGN KEY (`sender_id`) REFERENCES `account` (`id`);

ALTER TABLE `transaction` ADD FOREIGN KEY (`receiver_id`) REFERENCES `account` (`id`);

ALTER TABLE `client` ADD FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);
