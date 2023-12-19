 CREATE TABLE homework (
 	id INT AUTO_INCREMENT NOT NULL,
 	name VARCHAR(256) NOT NULL,
 	description VARCHAR(256) NOT NULL,
    PRIMARY KEY(id)
 );

 CREATE TABLE lesson (
 	 id INT AUTO_INCREMENT NOT NULL,
     name VARCHAR(256) NOT NULL,
     updateAt datetime NOT NULL,
     homework_id int NOT NULL,
     PRIMARY KEY(id),
     constraint foreign key(homework_id) references homework(id)
 );

 CREATE TABLE t_schedule (
 	 id INT AUTO_INCREMENT NOT NULL,
     name VARCHAR(256) NOT NULL,
     updateAt datetime NOT NULL,
     PRIMARY KEY(id)
 );

 CREATE TABLE schedule_lesson (
 	 id INT AUTO_INCREMENT NOT NULL,
     schedule_id INT NOT NULL,
     lesson_id INT NOT NULL,
     PRIMARY KEY(id),
     CONSTRAINT FOREIGN KEY (lesson_id) REFERENCES lesson(id),
     CONSTRAINT FOREIGN KEY (schedule_id) REFERENCES t_schedule(id)
 );

 INSERT INTO homework (name, description) VALUES
 ('Math Assignment', 'Solve problems from Chapter 5.'),
 ('English Essay', 'Write an essay on your favorite book.'),
 ('Science Project', 'Create a model of the solar system.');

 INSERT INTO lesson (name, updateAt, homework_id) VALUES
 ('Algebra Basics', '2023-12-01 10:00:00', 1),
 ('Literary Analysis', '2023-12-01 11:30:00', 2),
 ('Space Exploration', '2023-12-01 14:00:00', 3);

 INSERT INTO t_schedule (name, updateAt) VALUES
 ('Monday Schedule', '2023-12-01 12:00:00'),
 ('Wednesday Schedule', '2023-12-01 13:30:00'),
 ('Friday Schedule', '2023-12-01 16:00:00');

 INSERT INTO schedule_lesson (schedule_id, lesson_id) VALUES
 (2, 1), (3, 2), (2, 3), (1, 1), (1, 2), (1, 3);