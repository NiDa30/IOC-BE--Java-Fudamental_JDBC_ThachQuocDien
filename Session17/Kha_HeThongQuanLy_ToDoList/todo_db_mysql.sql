-- Database: todo_db

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- 1. Add task
DELIMITER //
CREATE PROCEDURE add_task(
    IN p_name VARCHAR(255),
    IN p_status VARCHAR(50)
)
BEGIN
    INSERT INTO tasks (task_name, status)
    VALUES (p_name, p_status);
END //
DELIMITER ;

-- 2. List tasks
DELIMITER //
CREATE PROCEDURE list_tasks()
BEGIN
    SELECT * FROM tasks;
END //
DELIMITER ;

-- 3. Update task status
DELIMITER //
CREATE PROCEDURE update_task_status(
    IN p_id INT,
    IN p_status VARCHAR(50)
)
BEGIN
    UPDATE tasks SET status = p_status WHERE id = p_id;
END //
DELIMITER ;

-- 4. Delete task
DELIMITER //
CREATE PROCEDURE delete_task(
    IN p_id INT
)
BEGIN
    DELETE FROM tasks WHERE id = p_id;
END //
DELIMITER ;

-- 5. Search task by name
DELIMITER //
CREATE PROCEDURE search_task_by_name(
    IN p_name VARCHAR(255)
)
BEGIN
    SELECT * FROM tasks WHERE task_name LIKE CONCAT('%', p_name, '%');
END //
DELIMITER ;

-- 6. Task statistics
DELIMITER //
CREATE PROCEDURE task_statistics(
    OUT p_completed INT,
    OUT p_not_completed INT
)
BEGIN
    SELECT COUNT(*) INTO p_completed FROM tasks WHERE status = 'đã hoàn thành';
    SELECT COUNT(*) INTO p_not_completed FROM tasks WHERE status = 'chưa hoàn thành';
END //
DELIMITER ;
