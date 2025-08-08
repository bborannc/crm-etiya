package com.boran.crm.domain.application;

import com.boran.crm.domain.entity.Task;
import com.boran.crm.domain.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    // Temel CRUD operasyonları
    Task createTask(Task task);
    Task updateTask(String id, Task task);
    void deleteTask(String id);
    Optional<Task> findById(String id);
    Page<Task> findAll(Pageable pageable);

    // Özel business metodları
    Task assignTask(String taskId, String userId);
    Task updateTaskStatus(String taskId, TaskStatus status);
    
    // Filtreleme ve arama metodları
    List<Task> findTasksByCustomerId(String customerId);
    List<Task> findTasksByAssignedUserId(String userId);
    List<Task> findOverdueTasks();
    List<Task> findTasksByStatus(TaskStatus status);
    List<Task> findTasksByDueDateBetween(LocalDateTime start, LocalDateTime end);
    
    // Dashboard ve raporlama metodları
    long countTasksByStatus(TaskStatus status);
    List<Task> findRecentTasks(int limit);
    List<Task> findUpcomingDeadlines(int days);
}