package com.boran.crm.domain.service.abstracts;

import com.boran.crm.domain.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    List<Task> findByCustomerId(Long customerId);
    List<Task> findByAssignedUserId(Long userId);
    Task update(Task task);
    void delete(Long id);
}
