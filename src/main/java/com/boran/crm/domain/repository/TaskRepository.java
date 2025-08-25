package com.boran.crm.domain.repository;

import com.boran.crm.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task ,Long> {
    List<Task> findByAssignedUserId(Long userId);
    List<Task> findByCustomerId(Long customerId);


}
