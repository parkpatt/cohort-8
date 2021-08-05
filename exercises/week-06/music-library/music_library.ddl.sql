drop database if exists music_library;
create database music_library;
use music_library;

create table person(
	person_id int auto_increment primary key,
	`name` varchar(256) not null
);

create table writer(
	writer_id int auto_increment primary key,
    person_id int not null,
    constraint fk_writer_person_id
		foreign key (person_id)
        references person(person_id)
);

create table artist(
	artist_id int auto_increment primary key,
    `name` varchar(256) not null
);

create table person_artist(
	person_id int not null,
    artist_id int not null,
    constraint fk_person_artist_person_id
		foreign key (person_id)
        references person(person_id),
	constraint fk_person_artist_artist_id
		foreign key (artist_id)
        references artist(artist_id)
);

create table album(
	album_id int auto_increment primary key,
    title varchar(256) not null,
	release_date datetime not null,
    label varchar(256) not null
);

create table artist_album(
	artist_id int not null,
    album_id int not null,
    constraint pk_artist_album
		primary key (artist_id, album_id),
	constraint fk_artist_album_artist_id
		foreign key (artist_id)
        references artist(artist_id),
	constraint fk_artist_album_album_id
		foreign key (album_id)
        references album(album_id)
);

create table track(
	track_id int auto_increment primary key,
    song_number int not null,
    title varchar(128) not null,
    `length` varchar(9) not null,
    album_id int not null,
    constraint fk_track_album_id
		foreign key (album_id)
        references album(album_id)
);

create table track_artist(
	track_id int not null,
    artist_id int not null,
    constraint fk_track_artist_track_id
		foreign key (track_id)
        references track(track_id),
	constraint fk_track_artist_artist_id
		foreign key (artist_id)
        references artist(artist_id)
);

create table track_person(
    track_id int not null,
	person_id int not null,
	constraint fk_track_writer_track_id
		foreign key (track_id)
        references track(track_id),
	constraint fk_track_person_person_id
		foreign key (person_id)
        references person(person_id)
);



