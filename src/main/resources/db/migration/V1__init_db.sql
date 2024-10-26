CREATE TABLE client (
                        ID INT PRIMARY KEY AUTO_INCREMENT,
                        NAME VARCHAR(1000) NOT NULL CHECK (CHAR_LENGTH(NAME) >= 2)
);