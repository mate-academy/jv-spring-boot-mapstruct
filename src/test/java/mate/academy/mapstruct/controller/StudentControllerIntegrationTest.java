package mate.academy.mapstruct.controller;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class StudentControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentRepository studentRepository;

    private MockMvc mockMvc;

    @Test
    public void testSave() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        CreateStudentRequestDto requestDto = new CreateStudentRequestDto();
        requestDto.setName("Bob Alison");
        requestDto.setEmail("bob.alison@example.com");
        requestDto.setGroupId(2L);
        requestDto.setSubjects(List.of(2L, 3L));

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Bob Alison\",\"email\":\"bob.alison@example.com\",\"groupId\":2,\"subjects\":[2,3]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectIds", containsInAnyOrder(2, 3)));
    }
}
