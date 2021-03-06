DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) DEFAULT '' NOT NULL,
  first_name VARCHAR(100) DEFAULT '' NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL,
  enabled BOOLEAN DEFAULT TRUE NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role VARCHAR(50) NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE messages (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  topic VARCHAR(100) NOT NULL,
  message TEXT NOT NULL,
  date_time TIMESTAMP NOT NULL,
  FOREIGN KEY messages_fk (user_id) REFERENCES users (id) ON DELETE CASCADE
);