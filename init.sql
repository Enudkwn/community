create database if not exists community;
use community;
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password CHAR(64) NOT NULL,
    intro TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    role ENUM('user', 'moderator', 'admin') DEFAULT 'user'
);
CREATE TABLE posts (
    post_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_user BIGINT NOT NULL,
    create_username VARCHAR(50) NOT NULL,
    like_count INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
#     view_count INT DEFAULT 0,
    FOREIGN KEY (create_user) REFERENCES users(user_id)
);
CREATE TABLE replies (
    reply_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT NOT NULL,
    create_user BIGINT NOT NULL,
    create_username VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    like_count INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (create_user) REFERENCES users(user_id)
);

ALTER TABLE replies
    DROP FOREIGN KEY replies_ibfk_1;

ALTER TABLE replies
    ADD CONSTRAINT replies_ibfk_1
        FOREIGN KEY (post_id) REFERENCES posts(post_id)
            ON DELETE CASCADE;