package com.swisscom.demo.taskapi;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class TaskDeleteTest extends BaseTest {
    @Test
    void create_thenDeleteTask() throws Exception {
        // 1. CREATE
        String createBody = """
        {
            "title": "Delete Me",
            "description": "Will be deleted",
            "completed": false
        }
        """;
        MvcResult createResult = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBody))
                .andReturn();
        String response = createResult.getResponse().getContentAsString();
        System.out.println("CREATE RESPONSE: " + response);
        long id = Long.parseLong(response.split("\"id\":")[1].split(",")[0]);
        // 2. DELETE
        MvcResult deleteResult = mockMvc.perform(delete("/tasks/" + id))
                .andReturn();
        System.out.println("DELETE RESPONSE CODE: " +
                deleteResult.getResponse().getStatus());
        // 3. VERIFY (GET should fail or return empty/404 depending on API)
        MvcResult verify = mockMvc.perform(get("/tasks/" + id))
                .andReturn();
        System.out.println("VERIFY STATUS AFTER DELETE: " +
                verify.getResponse().getStatus());
    }
}
