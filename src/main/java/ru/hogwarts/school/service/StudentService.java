package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;


public interface StudentService {

    Student creatStudent(Student student);

    Student findStudentById(Long id);

    Student editStudent(Student student);

    Student deleteStudent(Long id);

    Collection<Student> getAllStudents();
}
