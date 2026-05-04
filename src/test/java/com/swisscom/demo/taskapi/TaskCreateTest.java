package com.swisscom.demo.taskapi;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskCreateTest extends BaseTest {
    @Test
    void createTask_andReturnId() throws Exception {
        String requestBody = """
        {
            "title": "Test Task",
            "description": "Created via test",
            "completed": false
        }
        """;
        MvcResult result = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        // PRINT RESPONSE
        String response = result.getResponse().getContentAsString();
        System.out.println("CREATE RESPONSE: " + response);
    }
    @Test
    void shouldFailWhenTitleIsEmpty() throws Exception {
        String body = """
        {
            "title": "",
            "description": "Invalid",
            "completed": false
        }
        """;

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}