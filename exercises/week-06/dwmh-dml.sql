use dwmh;


insert into state_province (`name`)
select 
	distinct host_state
from reservations_import
union
select 
	distinct guest_state 
from reservations_import;


insert into person(
	email,
	first_name,
    last_name,
    phone
)
select 
	distinct host_email,
	host_first_name,
    host_last_name,
    host_phone
from reservations_import
union
select 
	distinct guest_email,
	guest_first_name,
	guest_last_name,
    guest_phone
from reservations_import;


insert into guest ( 
	person_id,
    state_province_id
) 
select distinct
	p.person_id,
    s.state_province_id
from person p
inner join reservations_import i on i.guest_email = p.email
inner join state_province s on s.`name` = i.guest_state;

