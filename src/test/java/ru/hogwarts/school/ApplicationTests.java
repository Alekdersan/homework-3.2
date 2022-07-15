package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
        Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/info", String.class))
				.isEqualTo("Student of this faculty is Good person!");
    }

    @Test
    public void testGetStudent() throws Exception {
        Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
				.isNotEmpty();
    }
    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Valentin");
        student.setAge(30);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testGetAllStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .isNotNull();
    }
}
