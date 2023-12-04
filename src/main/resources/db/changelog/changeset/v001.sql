
create table manager (
                        id int primary key auto_increment not null,
                        first_name varchar(50) not null,
                        last_name varchar(50) not null,
                        status int(1),
                        email varchar(50) not null unique,
                        created_at timestamp,
                        updated_at timestamp
);
create table client (
                         id bigint primary key auto_increment not null,
                         manager_id int not null references manager(id),
                         status int(1),
                         tax_code varchar(20),
                         first_name varchar(50) not null,
                         last_name varchar(50) not null,
                         email varchar(60) not null unique,
                         address varchar(50),
                         phone varchar(20),
                         created_at timestamp,
                         updated_at timestamp
);
create table product (
                        id bigint primary key auto_increment not null,
                        name varchar(75) not null,
                        status int(1),
                        currency_code varchar(2),
                        interest_rate decimal(4, 2),
                        credit_limit decimal(9, 2),
                        created_at timestamp,
                        updated_at timestamp
);
create table account (
                         id bigint primary key auto_increment not null,
                         client_id bigint not null references client(id),
                         name varchar(75),
                         type int(1),
                         status int(1),
                         balance decimal(12, 2),
                         currency_code varchar(2),
                         created_at timestamp,
                         updated_at timestamp
);
create table agreement (
                           id bigint primary key auto_increment not null,
                           manager_id int not null references manager(id),
                           account_id bigint not null references account(id),
                           product_id bigint not null references product(id),
                           status int(1),
                           sum decimal(10, 2),
                           created_at timestamp,
                           updated_at timestamp

);
create table transaction (
                             id bigint primary key auto_increment not null,
                             debit_account_id bigint not null references account(id),
                             credit_account_id bigint not null references account(id),
                             type int(1),
                             amount decimal(9, 3),
                             description varchar(255),
                             created_at timestamp
)
