create table APP
(
    ID                 BIGINT identity (1,1) PRIMARY KEY,
    HOST_ID            BIGINT REFERENCES HOST (ID),
    IMAGE_ID           BIGINT REFERENCES IMAGE (ID),
    PROJECT_IDENFIFIER VARCHAR(20),
    PORT               VARCHAR(6),
    IMAGE              VARCHAR(100),
    PROJECT_NAME       VARCHAR(20),
    CREATE_DATE        DATETIME2
);
