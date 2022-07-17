package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Set;


public interface StudentService {

    Student creatStudent(Student student);


    Student findStudentById(Long id);


    Student editStudent(Student student);

    void deleteStudent(Long id);

    List<Student> getAllStudents();

    Set<Student> getByAge(int age);

    Set<Student> findAllStudentsByAgeBetween(Integer minAge, Integer maxAge);

    Double getAverageAgeOfAllStudents();

    Set<Student> findStudentsByNameContains(String part);

    List<String> getAllStudentsWithLetterA();

    void getStudentsThread();

    void run(int id);

    void printNameStudent();
}
