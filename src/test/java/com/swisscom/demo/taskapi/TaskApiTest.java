package com.swisscom.demo.taskapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swisscom.demo.taskapi.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class TaskApiTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;


    // ---------- GET ----------
    @Test
    void shouldGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    // ---------- POST ----------
    @Test
    void shouldCreateTask() throws Exception {


        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Created via test");
        task.setCompleted(false);


        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Task"));
    }


    // ---------- PUT ----------
    @Test
    void shouldUpdateTask() throws Exception {


        // Step 1: Create a task
        Task task = new Task();
        task.setTitle("Initial Task");
        task.setDescription("Initial desc");
        task.setCompleted(false);


        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andReturn()
                .getResponse()
                .getContentAsString();


        Task createdTask = objectMapper.readValue(response, Task.class);


        // Step 2: Update the created task
        Task updated = new Task();
        updated.setTitle("Updated Task");
        updated.setDescription("Updated desc");
        updated.setCompleted(true);


        mockMvc.perform(put("/tasks/" + createdTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"));
    }


    // ---------- DELETE ----------
    @Test
    void shouldDeleteTask() throws Exception {


        // Create task first
        Task task = new Task();
        task.setTitle("Task to delete");
        task.setDescription("desc");
        task.setCompleted(false);


        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andReturn()
                .getResponse()
                .getContentAsString();


        Task createdTask = objectMapper.readValue(response, Task.class);


        // Delete it
        mockMvc.perform(delete("/tasks/" + createdTask.getId()))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
