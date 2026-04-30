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
}