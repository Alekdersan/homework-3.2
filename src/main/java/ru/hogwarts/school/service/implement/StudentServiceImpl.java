package ru.hogwarts.school.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student creatStudent(Student student) {
        logger.info("Was invoked method for create student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        logger.info("Finding for a student by ID = {}", id);
        return studentRepository.findById(id).get();
    }


    @Override
    public Student editStudent(Student student) {
        logger.debug("Editing a student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.debug("Removing a student by ID from the lists: {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        logger.info("Get a list of all students");
        return studentRepository.findAll();
    }

    public Set<Student> getByAge(int age) {
        logger.info("Get the age of students = {}", age);
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toSet());
    }

    public Set<Student> findAllStudentsByAgeBetween(Integer min, Integer max) {
        logger.info("Find students by a given age between min = {} and max = {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Set<Student> findStudentsByNameContains(String part) {
        logger.info("Search for students by name part: {}", part);
        return studentRepository.findByNameContainsIgnoreCase(part);
    }
}
