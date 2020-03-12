create table HOST
(
    ID       SERIAL PRIMARY KEY,
    STAGE_ID INTEGER REFERENCES STAGE (ID),
    NAME     VARCHAR(30),
    URL      VARCHAR(30)
);
INSERT INTO HOST
VALUES (default, 1, 'docker-01', 'http://etw-docker-01.bvaetw.de')