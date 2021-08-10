use music_library;

insert into person (`name`)
values 
	("Beyonce Knowles"), -- 1
	("Kendrick Duckworth"), -- 2
	("Christopher Breaux"), -- 3
	("Shawn Carter"), -- 4
	("Jack White"),  -- 5
	("Sia Furler"), -- 6
	("Brian Soko"), -- 7
	("Jerome Harmon"), -- 8
	("Malay Ho"), -- 9
	("Andre Eric Proctor"), -- 10
	("Rasool Díaz"), -- 11
	("Timothy Mosley"), -- 12
	("Noel Fisher"), -- 13
	("Pharrell Williams"), -- 14
	("Joshua Coleman"), -- 15
	("Diana Gordon"), -- 16
	("Rhoden"), -- 17
	("Jonathan Coffer"), -- 18
	("Carla Williams"), -- 19
	("Arrow Benjamin"), -- 20
	("Kanye West") -- 21 
;

insert into artist (`name`)
values 
	("Beyonce"), -- 1
	("Kendrick Lamar"), -- 2
	("Frank Ocean"), -- 3
	("Jay-Z"), -- 4
	("Jack White") -- 5
;

insert into person_artist (person_id, artist_id)
values 
	(1,1), 
	(2,2), 
	(3,3), 
	(4,4), 
	(5,5)
;

insert into album (title, release_date, label)
values 
	("Beyonce", "2013-12-13", "Columbia"), -- 1
	("Lemonade", "2021-07-29", "Columbia"), -- 2
    ("Frank Ocean", "2016-08-20", "Boys Don't Cry") -- 3
;

insert into artist_album (artist_id, album_id)
values 
	(1,1),
    (1,2),
    (3,3)
;

insert into track (song_number, title, length, album_id)
values
	(1, "Pretty Hurts",	"4:17", 1), -- 1
    (3,	"Drunk in Love", "5:23", 1), -- 2
    (12, "Super-Power", "4:36", 1), -- 3
    (3, "Don't Hurt Yourself", "3:53", 2), -- 4
    (4, "Sorry", "3:52", 2), -- 5
    (10, "Freedom", "5:53", 2), -- 6
    (2, "Nikes", "5:14", 3), -- 7
    (15, "White Ferrari", "4:08", 3), -- 8
    (17, "Godspeed", "2:57", 3) -- 9
;

insert into track_artist (track_id, artist_id)
values
	(1, 1),
    (2, 1),
    (2, 4),
    (3, 1),
    (3, 3),
    (4, 1),
    (4, 5),
    (5, 1),
    (6, 1),
    (6, 2),
    (7, 3),
    (8, 3),
    (9, 3)
;

insert into track_person (track_id, person_id)
values 
	(1, 1),
    (1, 15),
    (1, 6),
    (2, 1),
    (2, 7),
	(2, 8),
    (2, 4),
	(2, 10),
    (2, 11),
	(2, 12),
    (2, 13),
    (3, 1),
    (3, 14),
    (3, 3),
    (4, 1),
    (4, 5),
    (4, 16),
    (5, 1),
    (6, 1),
    (6, 2),
    (6, 18),
    (6, 19),
    (6, 20),
    (7, 3),
    (8, 3),
	(8, 21),
	(8, 9),
    (9, 3),
    (9, 9)
;

CREATE TABLE shirts (
    name VARCHAR(40),
    size ENUM('x-small', 'small', 'medium', 'large', 'x-large')
);

-- TODO: practice updates and deletes 