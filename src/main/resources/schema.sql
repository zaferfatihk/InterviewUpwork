DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
    id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    height VARCHAR(255) NOT NULL,
    version INT NOT NULL,
    PRIMARY KEY (id)
);