package ru.hogwarts.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.implement.StudentServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @InjectMocks
    private StudentController studentController; // добавил как в разборе

//    @Autowired
//    private StudentController studentController;  - закоммитил т.к. в разборе нет

    @Autowired
    private StudentServiceImpl studentService; // добавил как в разборе

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        Student student = new Student("Valentin", 30, 10L);
        studentService.creatStudent(student);
        when(studentRepository.findById(10L)).thenReturn(Optional.of(student));
    }

    @AfterEach
    void tearUp() {
        studentService.deleteStudent(10L);
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testGetStudent() throws JSONException {

        String expected = "{id:10,title:\"Valentin\",age:30}";

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/student/" + 10L, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    void creatStudent() throws JsonProcessingException, JSONException {

        Student student = new Student("Bob", 16, 10L);
        studentService.creatStudent(student);
        String expected = mapper.writeValueAsString(student);

        ResponseEntity<String> response = restTemplate.postForEntity("/student", student, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void editStudent() {

        Student student = new Student("Bob", 16, 10L);
        when(studentRepository.save(any())).thenReturn(student);
        HttpEntity<Student> entity = new HttpEntity<>(student);

        ResponseEntity<Student> response = this.restTemplate.exchange("/student", HttpMethod.PUT, entity, Student.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void deleteStudent() {

        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("/student/10", HttpMethod.DELETE, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}