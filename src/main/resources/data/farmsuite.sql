CREATE TABLE farmsuitedatabase.users (
    id INT PRIMARY KEY AUTO_INCREMENT,   -- id: Auto-incrementing integer for unique user ID
    username VARCHAR(255) NOT NULL,       -- username: A string column for the user's username (max length 255)
    password VARCHAR(255) NOT NULL,       -- password: A string column for the user's password (max length 255)
    dailytask TEXT,                       -- dailytask: A text column for the user's daily tasks
    pay DECIMAL(10, 2),                   -- pay: A decimal column to store payment, allowing up to 10 digits, 2 after the decimal
    title VARCHAR(100),                   -- title: A string column for the user's title (max length 100)
    purchasekey VARCHAR(255)                    -- key: A string column to store a key (max length 255), backtick is used because 'key' is a reserved word
);
INSERT INTO farmsuitedatabase.users (username, password, dailytask, pay, title, purchasekey)
VALUES ('waste1', 'waste1', NULL, NULL, 'SECONDARY', NULL);

UPDATE farmsuitedatabase.users
SET pay = '50'
WHERE id = '8';

select * from users;
UPDATE users
SET username = 'wastecollector'
WHERE id =8;

CREATE TABLE farmsuitedatabase.sales (
    id INT PRIMARY KEY AUTO_INCREMENT,   
    date DATE NOT NULL,      
    month VARCHAR(255) NOT NULL,      
    milk DECIMAL(10, 2),   
	yogurt DECIMAL(10, 2),     
    cheese DECIMAL(10, 2),  
    totalbill DECIMAL(10, 2) 
);

CREATE TABLE farmsuitedatabase.production (
    id INT PRIMARY KEY AUTO_INCREMENT,   
    date DATE NOT NULL,      
    month VARCHAR(255) NOT NULL,      
    milk DECIMAL(10, 2),   
	yogurt DECIMAL(10, 2),     
    cheese DECIMAL(10, 2)
);

INSERT INTO farmsuitedatabase.production (date, month, milk, yogurt, cheese)
VALUES ('2024-11-25', 'november', 18, 15, 7);

CREATE TABLE farmsuitedatabase.card (
    id INT PRIMARY KEY AUTO_INCREMENT,  
    name VARCHAR(255) NOT NULL, 
    cardNum VARCHAR(23) NOT NULL,
    date DATE NOT NULL,      
    cvc varchar(3) NOT NULL   
);

INSERT INTO farmsuitedatabase.card (name, cardNum, date, cvc)
VALUES ('SARIM', '1111-2222-3333-4444', '2024-11-22', '123');

drop table card;

ALTER TABLE farmsuitedatabase.production
ADD COLUMN sold_milk DECIMAL(10, 2) DEFAULT 0,
ADD COLUMN sold_yogurt DECIMAL(10, 2) DEFAULT 0,
ADD COLUMN sold_cheese DECIMAL(10, 2) DEFAULT 0;

select * from sales;

CREATE TABLE farmsuitedatabase.messages (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    date DATE NOT NULL,
    sender VARCHAR(255) NOT NULL,
    reciever VARCHAR(255) NOT NULL,
    message VARCHAR(255),
    status VARCHAR(10) NOT NULL
);
DELETE FROM farmsuitedatabase.messages
WHERE id = 4;
select * from messages;
INSERT INTO farmsuitedatabase.messages (date, sender, reciever, message, status)
VALUES ('2024-11-24', 'manager', 'wastecollector', 'please collect.', 'ASSIGNED');
select * from users;
SELECT * FROM messages WHERE reciever = 'vet';

CREATE TABLE Cows (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    month VARCHAR(20) NOT NULL,
    cow1 DECIMAL(5, 2) DEFAULT 0.00,
    cow2 DECIMAL(5, 2) DEFAULT 0.00,
    cow3 DECIMAL(5, 2) DEFAULT 0.00,
    cow4 DECIMAL(5, 2) DEFAULT 0.00,
    cow5 DECIMAL(5, 2) DEFAULT 0.00,
    cow6 DECIMAL(5, 2) DEFAULT 0.00,
    totalProduct DECIMAL(6, 2) DEFAULT 0.00
);

drop table cows;
select * from production;
select * from cows;
DELETE FROM cows WHERE id=3;
DELETE FROM production WHERE id=6;

CREATE TABLE dailytasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    sender VARCHAR(255),
    receiver VARCHAR(255),
    task VARCHAR(255),
    message VARCHAR(1000),
    reply VARCHAR(1000),
    status VARCHAR(50)
);
select * from dailytasks;
delete from dailytasks where id=1;
INSERT INTO farmsuitedatabase.dailytasks (date, sender, receiver, task, message, reply, status)
VALUES ('2024-11-24', 'manager', 'worker1', 'did you milk the cows yet?', 'I think worker 2 might have done it, but please check.', NULL, 'ASSIGNED');