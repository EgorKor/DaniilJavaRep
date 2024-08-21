SET search_path = test;
--CRUD операции (CREATE, READ, UPDATE, DELETE)
-- Пример CREATE - команда INSERT
-- ВСТАВИТЬ В ТАБЛИЦУ users ПОЛЯ username, password
-- ПЕРЕДАЕМ ЗНАЧЕНИЯ
-- ID не вставляем, т к генерация происходит автоматически
INSERT INTO users (username, password, email, role) VALUES
                                                  ('egor','12345678','egor@mail.com', 'ROLE_USER'), -- вставка первой записи
                                                  ('max','12345678','max@mail.com', 'ROLE_USER'), -- вставка второй записи
                                                  ('admin','$2a$10$kl7zWBnziFreIqLnOOO1qefyVYMZTPemJuPj1fCiEhRW3gID8vSY2', 'admin', 'ROLE_ADMIN');


INSERT INTO orders (name, price, user_id) VALUES
                                              ('order1',100,1),
                                              ('order2',200,1),
                                              ('order3',300,2);

INSERT INTO companies (name) VALUES
                                 ('Mac'),
                                 ('KFC');

INSERT INTO users_companies (user_id, company_id) VALUES
                                                      (1,1),
                                                      (1,2),
                                                      (2,1),
                                                      (2,2)