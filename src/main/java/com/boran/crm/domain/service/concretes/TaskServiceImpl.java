package com.boran.crm.domain.service.concretes;

import com.boran.crm.domain.entity.Task;
import com.boran.crm.domain.repository.TaskRepository;
import com.boran.crm.domain.service.abstracts.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByCustomerId(Long customerId) {
        return taskRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Task> findByAssignedUserId(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
