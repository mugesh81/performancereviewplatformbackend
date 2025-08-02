package com.example.performancereview.service;

import com.example.performancereview.model.ReviewTemplate;
import com.example.performancereview.repository.ReviewTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewTemplateService {

    private final ReviewTemplateRepository reviewTemplateRepository;

    public ReviewTemplateService(ReviewTemplateRepository reviewTemplateRepository) {
        this.reviewTemplateRepository = reviewTemplateRepository;
    }

    public ReviewTemplate addReviewTemplate(ReviewTemplate reviewTemplate) {
        return reviewTemplateRepository.save(reviewTemplate);
    }

    public List<ReviewTemplate> getAllTemplates() {
        return reviewTemplateRepository.findAll();
    }
}
