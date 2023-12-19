 SELECT * FROM homework;

 SELECT l.id, l.name, l.updateAt, h.name AS homework_name, h.description AS homework_description FROM lesson AS l
 JOIN homework AS h ON l.homework_id = h.id;

 SELECT l.id, l.name, l.updateAt, h.name AS homework_name, h.description AS homework_description FROM lesson AS l
 JOIN homework AS h ON l.homework_id = h.id
 ORDER BY l.updateAt DESC;

 SELECT s.id, s.name, s.updateAt, l.name AS lesson_name, l.updateAt AS lesson_updateAt FROM schedule_lesson AS sl
 JOIN t_schedule AS s ON sl.schedule_id = s.id
 JOIN lesson AS l ON sl.lesson_id = l.id;

 SELECT s.id, s.name, count(*) FROM schedule_lesson as sl
 JOIN t_schedule AS s ON sl.schedule_id = s.id
 JOIN lesson AS l ON sl.lesson_id = l.id
 GROUP BY s.id;
