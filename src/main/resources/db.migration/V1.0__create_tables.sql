create table payment_calculation
(
    id     int    not null primary key serial,
    amount double not null,
    rate   double not null
);

create table payment
(
    id             int    not null primary key serial,
    number         int    not null,
    amount         double not null,
    date           date   not null,
    calculation_id int    not null,
    foreign key (calculation_id) references payment_calculation
)