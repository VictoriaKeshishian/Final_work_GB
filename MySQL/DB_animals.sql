CREATE DATABASE Human_friends; 
USE Human_friends;

-- Создать таблицы с иерархией из диаграммы в БД
CREATE TABLE roditelskiy_klass (
  id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE domashnie_zhivotnye (
  id INT PRIMARY KEY AUTO_INCREMENT,
  roditelskiy_klass_id INT,
  FOREIGN KEY (roditelskiy_klass_id) REFERENCES roditelskiy_klass(id)
);

CREATE TABLE vyuchnye_zhivotnye (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  roditelskiy_klass_id INT,
  FOREIGN KEY (roditelskiy_klass_id) REFERENCES roditelskiy_klass(id)
);

CREATE TABLE sobaki (
  id INT PRIMARY KEY AUTO_INCREMENT,
  domashnie_zhivotnye_id INT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  FOREIGN KEY (domashnie_zhivotnye_id) REFERENCES domashnie_zhivotnye(id)
);

CREATE TABLE koshki (
  id INT PRIMARY KEY AUTO_INCREMENT,
  domashnie_zhivotnye_id INT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  FOREIGN KEY (domashnie_zhivotnye_id) REFERENCES domashnie_zhivotnye(id)
);

CREATE TABLE homyaki (
  id INT PRIMARY KEY AUTO_INCREMENT,
  domashnie_zhivotnye_id INT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  FOREIGN KEY (domashnie_zhivotnye_id) REFERENCES domashnie_zhivotnye(id)
);


CREATE TABLE loshadi (
  id INT PRIMARY KEY AUTO_INCREMENT,
  vyuchnye_zhivotnye_id INT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  FOREIGN KEY (vyuchnye_zhivotnye_id) REFERENCES vyuchnye_zhivotnye(id)
);

CREATE TABLE varbludy (
  id INT PRIMARY KEY AUTO_INCREMENT,
  vyuchnye_zhivotnye_id INT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  FOREIGN KEY (vyuchnye_zhivotnye_id) REFERENCES vyuchnye_zhivotnye(id)
);

CREATE TABLE osly (
  id INT PRIMARY KEY AUTO_INCREMENT,
  vyuchnye_zhivotnye_id INT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  FOREIGN KEY (vyuchnye_zhivotnye_id) REFERENCES vyuchnye_zhivotnye(id)
);
-- Заполнить низкоуровневые таблицы именами(животных), командами, которые они выполняют и датами рождения
INSERT INTO sobaki (name, komanda, data_rozhdeniya) VALUES 
('Шарик', 'Сидеть', '2020-03-05'),
('Бобик', 'Лежать', '2021-11-12'),
('Рекс', 'Голос', '2019-05-23');

INSERT INTO koshki (name, komanda, data_rozhdeniya) VALUES
('Мурка', 'К ноге', '2022-01-14'),  
('Пушок', 'Фас', '2020-09-08'),
('Васька', 'Голос', '2021-07-03');

INSERT INTO homyaki (name, komanda, data_rozhdeniya) VALUES
('Пупсень', 'Колесо', '2023-03-10'),  
('Вупсень', 'Спать', '2020-01-05'),
('Сергей', 'Есть', '2021-08-04');

INSERT INTO loshadi (name, komanda, data_rozhdeniya) VALUES
('Пупа', 'Шаг', '2019-05-11'),  
('Лупа', 'Рысь', '2020-06-13'),
('Александр', 'Галоп', '2018-07-03');

INSERT INTO varbludy (name, komanda, data_rozhdeniya) VALUES
('Аро', 'Лево', '2019-08-14'),  
('Абу', 'Пошел', '2020-11-13'),
('Алладин', 'Стой', '2023-07-03');

INSERT INTO osly (name, komanda, data_rozhdeniya) VALUES
('Зойка', 'Вправо', '2019-08-14'),  
('Машка', 'Пошел', '2020-11-13'),
('Марла', 'Стой', '2023-07-03');

-- Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
-- питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
DELETE FROM varbludy;

INSERT INTO loshadi (name, komanda, data_rozhdeniya)
SELECT name, komanda, data_rozhdeniya FROM osly;

DROP TABLE osly;

-- Создать новую таблицу “молодые животные” в которую попадут все
-- животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
-- до месяца подсчитать возраст животных в новой таблице

CREATE TABLE molodye_zhivotnye (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE
);

INSERT INTO molodye_zhivotnye (name, komanda, data_rozhdeniya)
SELECT name, komanda, data_rozhdeniya FROM sobaki
WHERE data_rozhdeniya BETWEEN DATE_SUB(NOW(), INTERVAL 3 YEAR) AND DATE_SUB(NOW(), INTERVAL 1 YEAR);

INSERT INTO molodye_zhivotnye (name, komanda, data_rozhdeniya)
SELECT name, komanda, data_rozhdeniya FROM koshki
WHERE data_rozhdeniya BETWEEN DATE_SUB(NOW(), INTERVAL 3 YEAR) AND DATE_SUB(NOW(), INTERVAL 1 YEAR);

INSERT INTO molodye_zhivotnye (name, komanda, data_rozhdeniya)
SELECT name, komanda, data_rozhdeniya FROM homyaki
WHERE data_rozhdeniya BETWEEN DATE_SUB(NOW(), INTERVAL 3 YEAR) AND DATE_SUB(NOW(), INTERVAL 1 YEAR);

INSERT INTO molodye_zhivotnye (name, komanda, data_rozhdeniya)
SELECT name, komanda, data_rozhdeniya FROM loshadi
WHERE data_rozhdeniya BETWEEN DATE_SUB(NOW(), INTERVAL 3 YEAR) AND DATE_SUB(NOW(), INTERVAL 1 YEAR);

DELETE FROM varbludy;

INSERT INTO molodye_zhivotnye (name, komanda, data_rozhdeniya)
SELECT name, komanda, data_rozhdeniya FROM varbludy
WHERE data_rozhdeniya BETWEEN DATE_SUB(NOW(), INTERVAL 3 YEAR) AND DATE_SUB(NOW(), INTERVAL 1 YEAR);

-- Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
-- прошлую принадлежность к старым таблицам.

CREATE TABLE vse_zhivotnye (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  komanda VARCHAR(100),
  data_rozhdeniya DATE,
  tip_zhivotnogo VARCHAR(50)
);

INSERT INTO vse_zhivotnye (name, komanda, data_rozhdeniya, tip_zhivotnogo)
SELECT name, komanda, data_rozhdeniya, 'sobaki' FROM sobaki;

INSERT INTO vse_zhivotnye (name, komanda, data_rozhdeniya, tip_zhivotnogo)
SELECT name, komanda, data_rozhdeniya, 'koshki' FROM koshki;

INSERT INTO vse_zhivotnye (name, komanda, data_rozhdeniya, tip_zhivotnogo)
SELECT name, komanda, data_rozhdeniya, 'homyaki' FROM homyaki;

INSERT INTO vse_zhivotnye (name, komanda, data_rozhdeniya, tip_zhivotnogo)
SELECT name, komanda, data_rozhdeniya, 'loshadi' FROM loshadi;

INSERT INTO vse_zhivotnye (name, komanda, data_rozhdeniya, tip_zhivotnogo)
SELECT name, komanda, data_rozhdeniya, 'varbludy' FROM varbludy;

DROP TABLE sobaki;
DROP TABLE koshki;
DROP TABLE homyaki;
DROP TABLE loshadi;
DROP TABLE varbludy;

SELECT * FROM vse_zhivotnye;




