drop database if exists dwmh;
create database dwmh;
use dwmh;

create table person (
	person_id int auto_increment primary key,
    first_name varchar(25) not null,
    last_name varchar(50) not null,
    email varchar(250) not null,
    phone varchar(25) not null
);

create table state_province(
	state_province_id int auto_increment primary key,
    `name` varchar(10) not null
);

create table guest (
	guest_id int auto_increment primary key,
    person_id int not null,
    state_province_id int not null,
    constraint fk_guest_person_id
		foreign key (person_id)
        references person(person_id),
	constraint fk_guest_state_province_id
		foreign key (state_province_id)
        references state_province(state_province_id)
);

create table `host` (
	host_id int auto_increment primary key,
    person_id int not null,
    standard_rate decimal(8,2) not null,
    weekend_rate decimal(8,2) not null,
    address varchar(250) not null,
    city varchar(150) not null,
    postal_code varchar(25) not null,
    state_province_id int not null,
    constraint fk_host_person_id
		foreign key (person_id)
        references person(person_id),
	constraint fk_host_state_province_id
		foreign key (state_province_id)
        references state_province(state_province_id)
);

create table reservation (
	reservation_id int auto_increment primary key,
	start_date datetime not null,
	end_date datetime not null,
	total decimal(8,2) not null,
	host_id int not null,
	guest_id int not null,
    constraint fk_reservation_host_id
		foreign key (host_id)
        references `host`(host_id),
	constraint fk_reservation_guest_id
		foreign key (guest_id)
        references guest(guest_id)
);


