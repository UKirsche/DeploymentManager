create table STAGE
(
    ID   BIGINT identity (1,1) PRIMARY KEY,
    NAME VARCHAR(30)
);

INSERT INTO STAGE (name)
VALUES ('ETW'),
       ('INT'),
       ('PRD')
