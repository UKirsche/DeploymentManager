create table APPLICATION
(
	ID SERIAL PRIMARY KEY ,
    IDENTIFIER VARCHAR (30) UNIQUE ,
    NAME VARCHAR (30),
    DESCRIPTION VARCHAR (100)
);