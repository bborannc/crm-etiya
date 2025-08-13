package com.boran.crm.controller;

import com.boran.crm.domain.entity.Task;
import com.boran.crm.domain.service.abstracts.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary="yeni task oluştur")
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.save(task));
    }

    @Operation(summary = "Tüm taskları getir")
    @GetMapping
    public ResponseEntity<List<Task>>getAllTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @Operation(summary = "ID'ye göre task getir")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Müşteriye ait taskları getir")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Task>> getTaskByCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(taskService.findByCustomerId(customerId));
    }
    @Operation(summary = "Atanan kullanıcıya ait taskları getir")
    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Task>> getTaskByAssignedUser(@PathVariable Long userId){
        return ResponseEntity.ok(taskService.findByAssignedUserId(userId));
    }
    @Operation(summary = "Task güncelle")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        if(taskService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        task.setId(id);
        return ResponseEntity.ok(taskService.update(task));
    }
    @Operation(summary = "Task sil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
