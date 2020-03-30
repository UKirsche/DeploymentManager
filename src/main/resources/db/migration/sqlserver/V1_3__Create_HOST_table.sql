create table HOST
(
    ID       BIGINT identity (1,1) PRIMARY KEY,
    STAGE_ID BIGINT REFERENCES STAGE (ID),
    NAME     VARCHAR(30),
    URL      VARCHAR(30)
);

