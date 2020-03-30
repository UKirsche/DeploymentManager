create table IMAGE
(
    ID                  BIGINT identity (1,1) PRIMARY KEY,
    PROJECT_ID          BIGINT REFERENCES PROJECT,
    TAG                 VARCHAR(30),
    CREATE_USER         VARCHAR(30),
    CREATE_DATE         DATETIME2,
    IMAGE               VARCHAR(100),
    COMMIT_HASH         VARCHAR(400),
    MAJOR_VERSION       INTEGER,
    MINOR_VERSION       INTEGER,
    INCREMENTAL_VERSION INTEGER,
    BUILD_NUMBER        INTEGER
);


