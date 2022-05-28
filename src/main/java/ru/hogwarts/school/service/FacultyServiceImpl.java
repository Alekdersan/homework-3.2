package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Set;


@Service
public class FacultyServiceImpl implements FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty creatFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

//    @Override
//    public Faculty getStudentByFaculty(Long id) {
//       return facultyRepository.findById(id).orElseThrow().getStudents();
    // или
// return facultyRepository.getStudentByFaculty(id);
//    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Set<Faculty> getByColor(String color) {
        return facultyRepository.getByColorIgnoreCase(color);
    }

    @Override
    public Set<Faculty> findFacultyByColorOrNameIgnoreCase(String color, String title) {
        return facultyRepository.findByColorIgnoreCaseOrTitleIgnoreCase(color, title);
    }
}
