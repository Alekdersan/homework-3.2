package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Set<Student> getByAge(int age);

    Set<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    Set<Student> findByNameContainsIgnoreCase(String part);
}
