CREATE DATABASE instagram;
DROP DATABASE instagram;

use instagram;
use netflix;

DROP TABLE user;
CREATE TABLE user (
 user_code SERIAL PRIMARY KEY,
 user_id VARCHAR(500) UNIQUE NOT NULL,
 user_email TEXT NOT NULL,
 user_pw TEXT NOT NULL,
 user_nick TEXT NOT NULL,
 user_phone VARCHAR(500) UNIQUE NOT NULL,
 user_post_list TEXT
);

DROP TABLE post;
CREATE TABLE post (
 post_code SERIAL PRIMARY KEY,
 post_text TEXT NOT NULL,
 post_tags TEXT NOT NULL,
 post_time DATE NOT NULL,
 post_owner INTEGER REFERENCES user,
 post_like_list TEXT
);

INSERT INTO user
(user_id, user_email, user_pw, user_nick, user_phone)
VALUES
('아이디22','이메일','비밀번호','닉네임','핸드폰2');


SELECT * FROM user WHERE user_id='never';

SELECT * FROM user;	
SELECT * FROM post order by post_code DESC;