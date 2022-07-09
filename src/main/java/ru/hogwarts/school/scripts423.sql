SELECT student.name, student.age, faculty.title
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT student
FROM student
INNER JOIN avatar a on student.id = a.student_id;