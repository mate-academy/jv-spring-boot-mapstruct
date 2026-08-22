package mate.academy.mapstruct.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:setup-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-up-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Check the first employee in the list
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].groupId").value(2))
                .andExpect(jsonPath("$[0].subjectIds").doesNotExist())

                // Check the second employee in the list
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"))
                .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$[1].groupId").value(3))
                .andExpect(jsonPath("$[1].subjectIds").doesNotExist());
    }

    @Test
    void testFindById() throws Exception {
        // Pre-populate database or set expectations here if necessary

        Long employeeId = 2L; // replace with actual id
        mockMvc.perform(get("/students/" + employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.groupId").value(2L))
                .andExpect(jsonPath("$.subjectIds", containsInAnyOrder(2, 3)));
    }

    @Test
    void testSave() throws Exception {
        // Define the request DTO here
        CreateStudentRequestDto requestDto = new CreateStudentRequestDto(
                "Bob Alison", "bob.alison@example.com", 2L, List.of(2L, 3L)
        );
        String requestBody = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Bob Alison"))
                .andExpect(jsonPath("$.email").value("bob.alison@example.com"))
                .andExpect(jsonPath("$.groupId").value(2L))
                .andExpect(jsonPath("$.subjectIds", containsInAnyOrder(2, 3)));
    }
}