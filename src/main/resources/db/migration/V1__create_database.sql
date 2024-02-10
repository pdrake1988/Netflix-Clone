CREATE TABLE account
(
    id       INT          NOT NULL AUTO_INCREMENT,
    username VARCHAR(20)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id         INT         NOT NULL AUTO_INCREMENT,
    account_id INT         NOT NULL,
    authority  VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE movie
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(20)  NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE genre
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE movie_genres
(
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE TABLE cast
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    age  INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE movie_cast
(
    movie_id INT NOT NULL,
    cast_id  INT NOT NULL,
    PRIMARY KEY (movie_id, cast_id),
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (cast_id) REFERENCES cast (id)
);

CREATE TABLE tv_show
(
    id          INT                NOT NULL AUTO_INCREMENT,
    name        VARCHAR(20) UNIQUE NOT NULL,
    description VARCHAR(255)       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tv_genres
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE tv_show_genres
(
    tv_show_id INT NOT NULL,
    genre_id   INT NOT NULL,
    PRIMARY KEY (tv_show_id, genre_id),
    FOREIGN KEY (tv_show_id) REFERENCES tv_show (id),
    FOREIGN KEY (genre_id) REFERENCES tv_genres (id)
);

CREATE TABLE tv_show_cast_members
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    age  INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tv_show_cast
(
    tv_show_id INT NOT NULL,
    cast_id    INT NOT NULL,
    PRIMARY KEY (tv_show_id, cast_id),
    FOREIGN KEY (tv_show_id) REFERENCES tv_show (id),
    FOREIGN KEY (cast_id) REFERENCES tv_show_cast_members (id)
);

CREATE TABLE movie_queue
(
    account_id INT NOT NULL,
    movie_id   INT NOT NULL,
    PRIMARY KEY (account_id, movie_id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (movie_id) REFERENCES movie (id)
);

CREATE TABLE tv_queue
(
    account_id INT NOT NULL,
    tv_show_id INT NOT NULL,
    PRIMARY KEY (account_id, tv_show_id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (tv_show_id) REFERENCES tv_show (id)
);
