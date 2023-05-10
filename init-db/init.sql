create table accounts (
   account_id bigserial primary key not null,
   document_number varchar(12) not null
);

create table operation_types (
   operation_type_id serial primary key,
   description varchar(50)
);

create table transactions(
    transaction_id bigserial primary key not null,
    account_id bigint not null,
    operation_type_id bigint not null,
    amount numeric (15,2) not null,
    event_date timestamp without time zone not null,
    constraint fk_accounts_id foreign key (account_id)
        references accounts (account_id),
    constraint fk_operations_type_id foreign key (operation_type_id)
        references operation_types (operation_type_id)
);

insert into accounts(document_number) values
('99999999999');

insert into operation_types(description) values
('COMPRA_A_VISTA'),
('COMPRA_PARCELADA'),
('SAQUE'),
('PAGAMENTO');

insert into transactions(account_id, operation_type_id, amount, event_date) values
(1,1,-50.0, current_timestamp),
(1,1,-23.5, current_timestamp),
(1,1,-18.7, current_timestamp),
(1,4, 60.0, current_timestamp);