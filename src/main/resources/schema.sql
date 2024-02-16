CREATE TABLE t_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE t_category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);

CREATE TABLE t_task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    task_status VARCHAR(255),
    created_time TIMESTAMP,
    deadline TIMESTAMP,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES t_category(id);
);

CREATE TABLE t_user_task (
    user_id INT,
    task_id INT,
    PRIMARY KEY (user_id, task_id),
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (task_id) REFERENCES t_task(id)
);

