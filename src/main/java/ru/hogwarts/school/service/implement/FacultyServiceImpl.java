package ru.hogwarts.school.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty creatFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.info("Finding for a faculty by ID = {}", id);
        return facultyRepository.findById(id).get();
    }


    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Editing a faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.debug("Removing a faculty by ID from the lists: {}", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        logger.info("Get a list of all faculties");
        return facultyRepository.findAll();
    }

    public Set<Faculty> getByColor(String color) {
        logger.info("Get the color of faculties = {}", color);
        return facultyRepository.getByColorIgnoreCase(color);
    }

    @Override
    public Set<Faculty> findFacultyByColorOrNameIgnoreCase(String color, String title ) {
        logger.info("Search for faculties by color {} or title {}", color, title);
        return facultyRepository.findByColorIgnoreCaseOrTitleIgnoreCase(color, title);
    }
}
