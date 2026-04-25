package com.swisscom.demo.taskapi.repository;

import com.swisscom.demo.taskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}