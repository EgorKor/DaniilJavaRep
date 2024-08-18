DROP SCHEMA IF EXISTS test CASCADE;
CREATE SCHEMA IF NOT EXISTS test;
SET search_path = test;

--Если существует - удалить таблицу users
--При удалении связанных таблиц, то есть на те которые ссылкаются
--нужно применять CASCADE
DROP TABLE IF EXISTS users CASCADE;

--Если не существует - создать таблицу users
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role VARCHAR NOT NULL
);

--Изменить таблицу users
--Добавить столбец email типа varchar не допуск NULL
--имеющий значение по умолчанию ''
--другие изменения - удалить столбец, поменять тип данных и т д
ALTER TABLE users
    ADD COLUMN email VARCHAR NOT NULL DEFAULT ('');


DROP TABLE IF EXISTS companies;

CREATE TABLE IF NOT EXISTS companies(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

DROP TABLE IF EXISTS users_companies;

CREATE TABLE IF NOT EXISTS users_companies(
    user_id BIGINT REFERENCES users(id),
    company_id BIGINT REFERENCES companies(id)
);

DROP TABLE IF EXISTS orders;

--Пример создания таблицы
CREATE TABLE orders
(
    id      BIGSERIAL PRIMARY KEY,                -- первичный ключ с автогенерацией id
    name    VARCHAR                      NOT NULL,
    price   INT                          NOT NULL,
    user_id BIGINT REFERENCES users (id) NOT NULL -- связь с таблицей users
);