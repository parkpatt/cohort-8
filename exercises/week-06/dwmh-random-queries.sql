use dwmh;

set sql_safe_updates = 1;

-- alter table state_province 
-- 	add column full_name varchar(25) null,
-- 	add constraint uq_state_province_name
-- 		unique (`name`);

-- alter table person
-- 	add constraint uq_person_email
-- 		unique(email) ;


-- alter table person
-- 	drop constraint uq_person_email;

insert into state_province (`name`) values ('MN');
insert into state_province (`name`) values ('NC');
insert into state_province (`name`) values ('CA');

insert into state_province (`name`)
values 
('HI'),
('OH'),
('IA');

select * from state_province;

insert into person (
	first_name,
    last_name,
    email,
    phone
) values (
	'Kathlyn',
    'Yearnes',
    'eyearnes0@sfgate.com',
    ''
);

insert into guest (
    person_id,
	state_province_id
) values (
	1,
    2
);


select * from guest;


update person set 
	first_name = 'Kathy',
    last_name = 'Lastynamey'
where email = 'eyearnes0@sfgate.com';







-- Kathlyn	Yearnes	eyearnes0@sfgate.com	(806) 1783815	3 Nova Trail	Amarillo	TX	79182	340	425	Blake	Naismith	bnaismithhr@skyrock.com	(915) 2180345	TX	7/31/2021	8/7/2021	2550
-- Kathlyn	Yearnes	eyearnes0@sfgate.com	(806) 1783815	3 Nova Trail	Amarillo	TX	79182	340	425	Deny	Lyness	dlynessy@icio.us	(704) 1597211	NC	3/23/2021	3/26/2021	1020
-- Kathlyn	Yearnes	eyearnes0@sfgate.com	(806) 1783815	3 Nova Trail	Amarillo	TX	79182	340	425	Lonny	Afonso	lafonsobd@discuz.net	(402) 2609934	NE	4/23/2021	4/26/2021	1190
-- Kathlyn	Yearnes	eyearnes0@sfgate.com	(806) 1783815	3 Nova Trail	Amarillo	TX	79182	340	425	Robbin	De Stoop	rdestoopdp@freewebs.com	(850) 4957875	FL	3/17/2022	3/22/2022	1870
-- Kathlyn	Yearnes	eyearnes0@sfgate.com	(806) 1783815	3 Nova Trail	Amarillo	TX	79182	340	425	Vail	Kerrane	vkerranepw@prweb.com	(760) 8760327	CA	1/26/2022	2/1/2022	2210
-- Kathlyn	Yearnes	eyearnes0@sfgate.com	(806) 1783815	3 Nova Trail	Amarillo	TX	79182	340	425	Mike	Gauler	mgaulerfd@pinterest.com	(213) 9709756	CA	12/25/2021	12/30/2021	1785
