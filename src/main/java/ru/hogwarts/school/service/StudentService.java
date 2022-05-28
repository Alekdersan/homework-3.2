package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface StudentService {

    Student creatStudent(Student student);

    Student findStudentById(Long id);

    Set<Student> getFacultyByStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    Collection<Student> getAllStudents();

    Set<Student> getByAge(int age);

    Set<Student> findAllStudentsByAgeBetween(Integer minAge, Integer maxAge);

    Set<Student> findStudentsByNameContains(String part);
}
