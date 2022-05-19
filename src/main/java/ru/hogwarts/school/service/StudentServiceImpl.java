package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class StudentServiceImpl implements StudentService{

private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student creatStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Set<Student> getByAge(int age) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toSet());
    }
}
