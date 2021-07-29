use sustainable_forage;

select
	sum(f.kilograms),
    i.name
from forage f
inner join item i on i.item_id = f.item_id
where foraged_on = '2020-06-20'
group by i.name
order by i.name;

select
	i.category,
    sum(i.dollars_per_kg * f.kilograms) as `total_value`
from forage f
inner join item i on i.item_id = f.item_id
where foraged_on = '2020-06-20'
group by i.category;
