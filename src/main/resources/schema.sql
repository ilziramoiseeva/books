CREATE TABLE categories
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    category_name TEXT NOT NULL
);

CREATE TABLE books
(
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    category_id INTEGER NOT NULL REFERENCES categories,
    name   TEXT    NOT NULL,
    price  INTEGER NOT NULL CHECK (price > 0),
    author TEXT    NOT NULL
);