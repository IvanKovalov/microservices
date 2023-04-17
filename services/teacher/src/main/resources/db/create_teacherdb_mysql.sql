CREATE database Teacher;
USE Teacher;
CREATE TABLE teacher (
    id INT NOT NULL AUTO_INCREMENT,
    Surname VARCHAR(100),
    Name VARCHAR(100),
    Patronymic VARCHAR(100),
    Posada VARCHAR(100),
    PRIMARY KEY (id)
);