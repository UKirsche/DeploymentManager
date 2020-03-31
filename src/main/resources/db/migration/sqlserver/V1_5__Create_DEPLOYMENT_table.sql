create table DEPLOYMENT
(
    ID          BIGINT identity (1,1) PRIMARY KEY,
    IMAGE_ID    BIGINT CONSTRAINT FK_DEPLOYMENT_IMAGE REFERENCES IMAGE,
    STAGE       VARCHAR(6),
    USER_       VARCHAR(20),
    CREATE_TIME DATETIME2
);
