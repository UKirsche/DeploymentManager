create table HOST
(
    ID       SERIAL PRIMARY KEY,
    STAGE_ID INTEGER REFERENCES STAGE (ID),
    NAME     VARCHAR(30),
    URL      VARCHAR(30)
);
INSERT INTO HOST
VALUES (default, 1, 'etw-docker-01', 'http://etw-docker-01.bvaetw.de'),
 (default, 1, 'etw-docker-02', 'http://etw-docker-02.bvaetw.de'),
 (default, 1, 'etw-docker-03', 'http://etw-docker-03.bvaetw.de'),
 (default, 1, 'int-docker-01', 'http://int-docker-01.bvaint.de'),
 (default, 1, 'int-docker-02', 'http://int-docker-02.bvaint.de'),
 (default, 1, 'int-docker-03', 'http://int-docker-03.bvaint.de'),
 (default, 1, 'etw-docker-02', 'http://etw-docker-02.bvaetw.de')
