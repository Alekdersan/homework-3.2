package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Set<Student> getByAge(int age);

    Set<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    Set<Student> findByNameContainsIgnoreCase(String part);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getCountOfAllStudents();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Double getAverageAgeOfAllStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 0 , 5", nativeQuery = true)
    Collection<Student> getLastFiveOfStudents();
}
