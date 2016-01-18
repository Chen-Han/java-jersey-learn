create table events(
	event_id INT NOT NULL PRIMARY KEY,
	event_date TIMESTAMP WITH TIME ZONE NOT NULL, 
	title varchar(255) NOT NULL
);

create table person(
	id INT NOT NULL PRIMARY KEY,
	age INT8 NOT NULL,
	firstname varchar(255) NOT NULL,
	lastname varchar(255) NOT NULL
);


create table person_event(
	event_id INT NOT NULL REFERENCES events(event_id),
	person_id INT NOT NULL REFERENCES person(id)
);


create sequence event_id_seq OWNED BY events.event_id start 1000 increment 10;
create sequence person_id_seq OWNED BY person.id start 1000 increment 10;