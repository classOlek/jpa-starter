package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByTimeBetweenAndAnalysisRequired(LocalDateTime start, LocalDateTime end, boolean toBeAnalyzed);

    Page<Event> findAll(Pageable pageable);

    @Modifying
    @Query("DELETE FROM Event e WHERE e.time < :time")
    void deleteInBulkBeforeDate(@Param("time") LocalDateTime time);

    @Modifying
    @Query("UPDATE Event e SET e.analysisRequired = true WHERE e.duration > :minDuration AND TYPE(e) = :clazz")
    void updateInBulkToBeAnalyzedByType(@Param("clazz") Class<? extends Event> clazz, @Param("minDuration") int minDuration);
}
