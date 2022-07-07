/*   SQL-запросы для создания ограничений:
*/

/*   1. Возраст студента не может быть меньше 16 лет.*/
CREATE TABLE student
(
    age INTEGER CHECK ( age > 16)
);

/*   добавление ограничений после заполения таблицы*/
ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 16);


/*   2.  Имена студентов должны быть уникальными и не равны нулю.*/
CREATE TABLE student
(
    name TEXT UNIQUE NOT NULL
);

/*   добавление ограничений после заполения таблицы*/
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

/*   3. Пара “значение названия” - “цвет факультета” должна быть уникальной.*/
ALTER TABLE faculty
    ADD CONSTRAINT title_color_unique UNIQUE (title, color);

/*   4.  При создании студента без возраста ему автоматически должно присваиваться 20 лет.*/
CREATE TABLE student(
    age INTEGER DEFAULT (age = 20)
);

/*   добавление ограничений после заполения таблицы*/
ALTER TABLE student
    ALTER age SET DEFAULT 20;