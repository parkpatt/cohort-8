use dwmh;

<<<<<<< HEAD
alter table state_province
	add column full_name varchar(150) null,
	add constraint uq_state_province_name
		unique(`name`)
;

alter table person
	add constraint uq_person_email
		unique(email)
;
=======
set sql_safe_updates = 1;

-- alter table state_province 
-- 	add column full_name varchar(25) null,
-- 	add constraint uq_state_province_name
-- 		unique (`name`);

-- alter table person
-- 	add constraint uq_person_email
-- 		unique(email) ;

>>>>>>> f9e52e62c47b4ead0d0da9f306b704c7964e1f5e

-- alter table person
-- 	drop constraint uq_person_email;

<<<<<<< HEAD
-- insert into state_province (`name`) 
-- values ('TX'), ('NC'), ('MN'), ('CA'), ('HI'), ('OH'), ('IA'), ('NY'), ("VT"), ("WA"), ('OR');

insert into state_province (state_province_id, `name`) 
values (1, 'TX'), (2, 'NC'), (3, 'MN'), (4, 'CA'), (5, 'HI'), (6, 'OH'), (7, 'IA'), (8, 'NY'), (9, "VT"), (10, "WA"), (11, 'OR');
=======
insert into state_province (`name`) values ('MN');
insert into state_province (`name`) values ('NC');
insert into state_province (`name`) values ('CA');

insert into state_province (`name`)
values 
('HI'),
('OH'),
('IA');
>>>>>>> f9e52e62c47b4ead0d0da9f306b704c7964e1f5e

select * from state_province;

insert into person (
	first_name,
    last_name,
    email,
<<<<<<< HEAD
    phone,
    state_province_id
) 
values (
	"Kathlyn",
    'Yearnes',
    'eyearnes0@sfgate.com',
    '(806) 1783815',
    1
);

select * from person;

insert into guest (person_id) values (1);

select * from guest;

insert into `host` (person_id, standard_rate, weekend_rate, street_address, city, postal_code)
values (1, 150, 250, "123 main street", "Dundas", "00000");

select * from host;

-- set sql_safe_updates = 0;

update person set
	first_name = "Kat"
where person_id = 1
;
    
    
    
    

-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Blake,Naismith,bnaismithhr@skyrock.com,(915) 2180345,TX,2021-07-31,2021-08-07,2550
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Deny,Lyness,dlynessy@icio.us,(704) 1597211,NC,2021-03-23,2021-03-26,1020
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Lonny,Afonso,lafonsobd@discuz.net,(402) 2609934,NE,2021-04-23,2021-04-26,1190
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Robbin,De Stoop,rdestoopdp@freewebs.com,(850) 4957875,FL,2022-03-17,2022-03-22,1870
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Vail,Kerrane,vkerranepw@prweb.com,(760) 8760327,CA,2022-01-26,2022-02-01,2210
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Mike,Gauler,mgaulerfd@pinterest.com,(213) 9709756,CA,2021-12-25,2021-12-30,1785
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Zorah,Halmkin,zhalmkinje@moonfruit.com,(801) 7805042,UT,2021-05-02,2021-05-08,2125
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Rodger,Cattrell,rcattrellft@histats.com,(323) 4668347,CA,2022-03-13,2022-03-15,680
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Fonz,Swayne,fswayne7y@creativecommons.org,(386) 2717907,FL,2021-10-04,2021-10-07,1020
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Dotti,Enderlein,denderleino1@prlog.org,(903) 8219601,TX,2021-12-14,2021-12-15,340
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Jerrine,Fancott,jfancottej@photobucket.com,(502) 3389093,KY,2022-01-16,2022-01-17,340
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Inge,Gout,igout5j@skype.com,(210) 6817663,TX,2021-10-19,2021-10-20,340
-- Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Heywood,Mellhuish,hmellhuish45@pen.io,(425) 2278177,WA,2021-11-01,2021-11-08,2550
=======
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
>>>>>>> f9e52e62c47b4ead0d0da9f306b704c7964e1f5e
