package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private Long currentId = 0L;
    private final Map<Long, Faculty> faculties = new HashMap<>();

    @PostConstruct
    public void init() {
        creatFaculty(new Faculty("Gryffindor", "Red"));
        creatFaculty(new Faculty("Hufflepuff", "Blue"));
        creatFaculty(new Faculty("Ravenclaw", "Yellow"));
        creatFaculty(new Faculty("Slytherin", "Green"));
    }

    @Override
    public Faculty creatFaculty(Faculty faculty) {
        faculty.setId(++currentId);
        faculties.put(currentId, faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    public List<Faculty> getByColor(String color) {
        return faculties.values().stream().filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

}
