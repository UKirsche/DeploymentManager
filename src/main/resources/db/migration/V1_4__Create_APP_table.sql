create table APP
(
    ID                     SERIAL PRIMARY KEY,
    HOST_ID                INTEGER REFERENCES HOST (ID),
    IMAGE_ID               INTEGER REFERENCES IMAGE (ID),
    APPLICATION_IDENFIFIER VARCHAR(20),
    PORT                   VARCHAR(6),
    IMAGE                  VARCHAR(100)
);