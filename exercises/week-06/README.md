
# Exercise: DDL/DML

## Goals

* Create a basic schema for the Don't Wreck My House project
* Populate your database using the provided denormalized data (i.e. the [`all-reservations.csv`](all-reservations.csv) file)
* Elaborate on the basic schema as time allows (i.e. stretch goals)

## DDL

Use DDL to create a database, tables, and appropriate relationships for hosts, guests, and reservations.

1. Identify tables and columns for each concept.
2. Create sensible names for the database, tables, and columns.
3. Use a database-generated primary key for all tables.
4. Determine sensible data types for each column.
5. Define relationships between concepts (one-to-one, one-to-many, many-to-many, self-referential).
6. Write the DDL and save it in a .sql file.
7. Execute the DDL and fix any errors.
8. Generate a database diagram with MySQL Workbench: Database -> Reverse Engineer -> Select your database -> Select your tables.

## DML

### Step 1

Write DML statements to insert the following hosts, guests, and reservations:

```csv
host_first_name,host_last_name,host_email,host_phone,host_address,host_city,host_state,host_postal_code,standard_rate,weekend_rate,guest_first_name,guest_last_name,guest_email,guest_phone,guest_state,start_date,end_date,total
Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Blake,Naismith,bnaismithhr@skyrock.com,(915) 2180345,TX,2021-07-31,2021-08-07,2550
Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Deny,Lyness,dlynessy@icio.us,(704) 1597211,NC,2021-03-23,2021-03-26,1020
Kathlyn,Yearnes,eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425,Lonny,Afonso,lafonsobd@discuz.net,(402) 2609934,NE,2021-04-23,2021-04-26,1190
Ernie,Harley,charley4@apple.com,(954) 7895760,1 Maple Wood Terrace,Orlando,FL,32825,176,220,Orelee,Scales,oscalespt@surveymonkey.com,(915) 6765839,TX,2021-09-14,2021-09-21,1320
Ernie,Harley,charley4@apple.com,(954) 7895760,1 Maple Wood Terrace,Orlando,FL,32825,176,220,Tierney,MacTrusty,tmactrustyhb@prlog.org,(610) 8450893,PA,2021-12-04,2021-12-11,1320
```

_Note: This step is intended to give you some practice with writing insert statements and to populate your schema with some initial data for testing purposes._

#### Test Queries

Write the following queries to test your schema:

1. Display a list of the hosts.
2. Display a list of the guests.
3. Display a list of the reservations with the associated host and guest information.

### Step 2

1. Use MySQL Workbench to import the provided denormalized data (i.e. the [`all-reservations.csv`](all-reservations.csv) file) into your database.
2. Write DML statements to populate your database from the imported denormalized table.
  * To do this, you'll need to write insert statements that are based on selects from the denormalized table.
  * **Be careful to eliminate duplicate rows in your selects!**
3. Use your test queries to confirm the data in your tables.

### Step 3

Write DML statements to make the following changes to your database:

1. For the host with the email `eyearnes0@sfgate.com`, update their first name to "Katie".
2. For the host with the email `charley4@apple.com`, delete the reservation with a start date of `2021-09-04` and an end date of `2021-09-09`.

## Elaborate (Stretch Goals)

1. Update your schema so that a host can have more than one location. Update your DDL and DML as necessary.
2. Update your schema so that each host is also a guest (so they can use their account to make reservations). Update your DDL and DML as necessary.
