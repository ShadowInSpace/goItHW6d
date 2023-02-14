CREATE SCHEMA IF NOT EXISTS hw6d;
CREATE TABLE IF NOT EXISTS hw6d.worker (
                                           ID SERIAL  PRIMARY KEY,
                                           NAME VARCHAR NOT NULL
                                               CHECK (CHAR_LENGTH(NAME)<=1000),
                                           BIRTHDAY DATE CHECK(BIRTHDAY >= '1901-01-01'),
                                           LEVEL VARCHAR NOT NULL
                                               CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
                                           SALARY INTEGER CHECK (SALARY >= 100 AND SALARY <=100000)
);
CREATE TABLE IF NOT EXISTS hw6d.client (
                                           ID SERIAL PRIMARY KEY,
                                           NAME VARCHAR NOT NULL
                                               CHECK (CHAR_LENGTH(NAME)<=1000)
);
CREATE TABLE IF NOT EXISTS hw6d.project (
                                            ID SERIAL PRIMARY KEY,
                                            CLIENT_ID INTEGER,
                                            START_DATA DATE,
                                            FINISH_DATA DATE
);
CREATE TABLE IF NOT EXISTS hw6d.project_worker (
                                                   PROJECT_ID INTEGER,
                                                   WORKER_ID INTEGER,
                                                   FOREIGN KEY (PROJECT_ID) REFERENCES hw6d.project(ID),
                                                   FOREIGN KEY (WORKER_ID) REFERENCES hw6d.worker(ID),
                                                   PRIMARY KEY(PROJECT_ID, WORKER_ID)
);
