package com.swisscom.demo.taskapi;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class TaskUpdateTest extends BaseTest {
    @Test
    void create_thenUpdateTask() throws Exception {
        // 1. CREATE
        String createBody = """
        {
            "title": "Old Title",
            "description": "Old Desc",
            "completed": false
        }
        """;
        MvcResult createResult = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBody))
                .andReturn();
        String createResponse = createResult.getResponse().getContentAsString();
        System.out.println("CREATE RESPONSE: " + createResponse);
        // SIMPLE ID EXTRACTION (for demo)
        // assuming response: {"id":1,...}
        long id = Long.parseLong(createResponse.split("\"id\":")[1].split(",")[0]);
        System.out.println("EXTRACTED ID: " + id);
        // 2. UPDATE
        String updateBody = """
        {
            "title": "Updated Title",
            "description": "Updated Desc",
            "completed": true
        }
        """;
        MvcResult updateResult = mockMvc.perform(put("/tasks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBody))
                .andReturn();
        System.out.println("UPDATE RESPONSE: " +
                updateResult.getResponse().getContentAsString());
    }
}