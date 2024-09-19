package mate.academy.mapstruct.controller;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class StudentControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GroupRepository groupRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // Ensure the group with ID 2 exists
        Group group = new Group();
        group.setId(2L);
        group.setName("Group 2");
        groupRepository.save(group);
    }

    @Test
    public void testSave() throws Exception {
        CreateStudentRequestDto requestDto = new CreateStudentRequestDto(
                "Bob Alison",
                "bob.alison@example.com",
                2L,
                List.of(2L, 3L)
        );

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Bob Alison\",\"email\":\"bob.alison@example.com\",\"groupId\":2,\"subjects\":[2,3]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectIds", containsInAnyOrder(2, 3)));
    }
}
