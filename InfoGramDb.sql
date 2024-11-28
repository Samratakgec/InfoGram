create database anudip ;
use anudip;
-- Create the User table with a Password field
CREATE TABLE User (
    User_ID VARCHAR(255) PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL
);

-- Create the Post table with a foreign key reference to User_ID in the User table
CREATE TABLE Post (
    Post_ID INT AUTO_INCREMENT PRIMARY KEY,
    PostTitle VARCHAR(255) NOT NULL,
    PostContent TEXT,
    CreatedByUser_ID VARCHAR(255),
    FOREIGN KEY (CreatedByUser_ID) REFERENCES User(User_ID) ON DELETE CASCADE
);

-- Create the Rating table to store ratings for each post given by users
CREATE TABLE Rating (
    Rating_ID INT AUTO_INCREMENT PRIMARY KEY,
    Post_ID INT,
    RatedByUser_ID VARCHAR(255),
    RatingScore TINYINT CHECK (RatingScore BETWEEN 1 AND 10),
    FOREIGN KEY (Post_ID) REFERENCES Post(Post_ID) ON DELETE CASCADE,
    FOREIGN KEY (RatedByUser_ID) REFERENCES User(User_ID) ON DELETE CASCADE
); 
-- drop database anudip ;
select * from user;
select * from post;
select * from rating ;
delete from user;