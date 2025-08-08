package com.boran.crm.domain.repository;

import com.boran.crm.domain.entity.Task;
import com.boran.crm.domain.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
    // Müşteri görevi çağırma
    List<Task> findByCustomerId(String customerId);
    
    // Kullanıcıy görevi
    List<Task> findByAssignedToId(String userId);
    
    // Duruma göre görevleri bulma
    List<Task> findByStatus(TaskStatus status);

    // TaskRepository'e eklenecek metodlar
    List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);
    List<Task> findTopNByOrderByCreatedAtDesc(int limit);
    List<Task> findByDueDateBetweenAndStatusNot(LocalDateTime start, LocalDateTime end, TaskStatus status);
    
    // overdue yani gecikenler
    @Query("SELECT t FROM Task t WHERE t.status != :completedStatus AND t.dueDate < :now")
    List<Task> findOverdueTasks(
        @Param("completedStatus") TaskStatus completedStatus,
        @Param("now") LocalDateTime now
    );
    
    // gün sınırlaması yapılırsa şu kadar gün kala mesela 7 gün kalaları göster gibi
    @Query("SELECT t FROM Task t WHERE t.status != :completedStatus " +
           "AND t.dueDate BETWEEN :start AND :end")
    List<Task> findUpcomingTasks(
        @Param("completedStatus") TaskStatus completedStatus,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );
    
    // kullanıcı statusune göre alma
    Page<Task> findByAssignedToIdAndStatus(
        String userId,
        TaskStatus status,
        Pageable pageable
    );
    
    // Önceliğe göre görevleri sıralama
    @Query("SELECT t FROM Task t WHERE t.status != :completedStatus " +
           "ORDER BY t.priority DESC, t.dueDate ASC")
    List<Task> findPriorityTasks(@Param("completedStatus") TaskStatus completedStatus);
    
    // Görev istatistikleri
    @Query("SELECT t.status, COUNT(t) FROM Task t GROUP BY t.status")
    List<Object[]> getTaskStatistics();
    
    // Tahmini süreye göre görevleri bulma
    List<Task> findByEstimatedHoursGreaterThan(Float hours);
    
    // Tamamlanma süresine göre görevleri bulma
    List<Task> findByCompletedAtBetween(LocalDateTime start, LocalDateTime end);
    
    // Müşteri ve durum bazlı görevleri bulma
    List<Task> findByCustomerIdAndStatus(String customerId, TaskStatus status);

    // Dashboard için gerekli metodlar
    long countByStatus(TaskStatus status);
    List<Task> findTop5ByStatusOrderByDueDateAsc(TaskStatus status);
    
    // Task trend analizi için
    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = :status AND " +
           "DATE(t.createdAt) = DATE(:date)")
    long countByStatusAndDate(
        @Param("status") TaskStatus status,
        @Param("date") LocalDateTime date
    );
}