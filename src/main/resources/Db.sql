CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);


CREATE TABLE task (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(50) NOT NULL,
                       user_id SERIAL references users(id)
);