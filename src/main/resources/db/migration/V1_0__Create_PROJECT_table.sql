create table PROJECT
(
	ID SERIAL PRIMARY KEY ,
    IDENTIFIER VARCHAR (30) UNIQUE ,
    NAME VARCHAR (30),
    BUILD_JOB VARCHAR (50),
    DEPLOY_JOB VARCHAR (50),
    DESCRIPTION VARCHAR (100)
);

INSERT INTO PROJECT (id, IDENTIFIER, NAME, DESCRIPTION, BUILD_JOB, DEPLOY_JOB)
VALUES
(DEFAULT, 'dmp', 'dmp-Fachanwendung', 'DMP', 'pipe_dmp', 'pipe_dmp_deploy'),
(DEFAULT, 'zus', 'Zuständige Stelle', 'ZUS', 'pipe_zustaendigestelle', 'pipe_zustaendigestelle_deploy');
