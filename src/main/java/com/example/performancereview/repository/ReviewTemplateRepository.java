package com.example.performancereview.repository;

import com.example.performancereview.model.ReviewTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTemplateRepository extends JpaRepository<ReviewTemplate, Long> {
}
