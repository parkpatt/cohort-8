use dwmh;

select * from reservations_import;

set sql_safe_updates = 0;
delete from state_province;
set sql_safe_updates = 1;

insert into state_province (`name`)
select
	distinct host_state
from reservations_import
union
select
	distinct guest_state
from reservations_import;

select * from state_province;

-- alter table person
-- 	drop constraint fk_guest_state_province_id,
-- 	drop column state_province_id;
--     
-- alter table guest
-- 	add column state_province_id int,
--     add constraint fk_guest_state_province_id
-- 		foreign key (state_province_id)
--         references state_province(state_province_id)
-- ;

-- alter table host
-- 	add column state_province_id int,
--     add constraint fk_host_state_province_id
-- 		foreign key (state_province_id)
--         references state_province(state_province_id)
-- ;

insert into person(
	email,
	first_name, 
    last_name,
    phone
)
select distinct host_email,
	host_first_name,
    host_last_name,
    host_phone
from reservations_import
union
select distinct guest_email,
	guest_first_name,
	guest_last_name,
    guest_phone
from reservations_import;

select * from person;

insert into guest (
	person_id,
    state_province_id
)
select
	distinct p.person_id,
    s.state_province_id
from person p 
inner join reservations_import i on i.guest_email = p.email
inner join state_province s on s.`name` = i.guest_state;

select * from guest;
