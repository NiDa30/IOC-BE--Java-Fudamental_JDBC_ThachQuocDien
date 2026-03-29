-- Database: todo_db

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL -- 'chưa hoàn thành' / 'đã hoàn thành'
);

-- 1. Add task
CREATE OR REPLACE PROCEDURE add_task(
    p_name VARCHAR,
    p_status VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO tasks (task_name, status)
    VALUES (p_name, p_status);
END;
$$;

-- 2. List tasks (using function to return table)
CREATE OR REPLACE FUNCTION list_tasks()
RETURNS TABLE (
    id INT,
    task_name VARCHAR,
    status VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT t.id, t.task_name, t.status FROM tasks t;
END;
$$;

-- 3. Update task status
CREATE OR REPLACE PROCEDURE update_task_status(
    p_id INT,
    p_status VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE tasks SET status = p_status WHERE id = p_id;
END;
$$;

-- 4. Delete task
CREATE OR REPLACE PROCEDURE delete_task(
    p_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM tasks WHERE id = p_id;
END;
$$;

-- 5. Search task by name
CREATE OR REPLACE FUNCTION search_task_by_name(p_name VARCHAR)
RETURNS TABLE (
    id INT,
    task_name VARCHAR,
    status VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT t.id, t.task_name, t.status 
    FROM tasks t 
    WHERE t.task_name ILIKE ('%' || p_name || '%');
END;
$$;

-- 6. Task statistics
CREATE OR REPLACE PROCEDURE task_statistics(
    OUT p_completed INT,
    OUT p_not_completed INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT COUNT(*) INTO p_completed FROM tasks WHERE status = 'đã hoàn thành';
    SELECT COUNT(*) INTO p_not_completed FROM tasks WHERE status = 'chưa hoàn thành';
END;
$$;
