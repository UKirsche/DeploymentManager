create table STAGE
(
    ID   SERIAL PRIMARY KEY,
    NAME VARCHAR(30)
);

INSERT INTO STAGE (id, name)
VALUES (DEFAULT, 'ETW');
INSERT INTO STAGE (id, name)
VALUES (DEFAULT, 'INT');
INSERT INTO STAGE (id, name)
VALUES (DEFAULT, 'PRD');