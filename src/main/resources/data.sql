INSERT INTO t_user (name) VALUES ('Alice'), ('Bob');

INSERT INTO t_category (name, description) VALUES ('Category 1', 'Description for Category 1'), ('Category 2', 'Description for Category 2');

INSERT INTO t_task (name, description, task_status, created_time, deadline, category_id)
VALUES ('Task 1', 'Description for Task 1', 'Pending', '2023-12-01 08:00:00', '2023-12-10 17:00:00', 1),
('Task 2', 'Description for Task 2', 'In Progress', '2023-12-02 10:00:00', '2023-12-15 12:00:00', 2);

INSERT INTO t_user_task (user_id, task_id) VALUES (1, 1), (2, 2);