CREATE TABLE lesson (
   id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(256) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE homework (
   id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(256) NOT NULL,
   description VARCHAR(256) NOT NULL,
   lesson_id INT NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT PRIMARY KEY lesson_id REFERENCES lesson(id) ON DELETE CASCADE
);

