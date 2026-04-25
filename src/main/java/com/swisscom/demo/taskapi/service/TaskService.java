package com.swisscom.demo.taskapi.service;

import com.swisscom.demo.taskapi.model.Task;
import com.swisscom.demo.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    // Constructor Injection (Spring automatically provides repository)
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Get all tasks (READ - all)
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // Get single task by ID (READ - one)
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Create new task (CREATE)
    public Task createTask(Task task) {
        return repository.save(task);
    }

    // Update existing task (UPDATE)
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id); // fetch existing task

        // update fields
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        return repository.save(task);
    }

    // Delete task (DELETE)
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}


