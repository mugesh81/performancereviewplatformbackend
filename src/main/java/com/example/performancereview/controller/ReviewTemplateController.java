package com.example.performancereview.controller;

import com.example.performancereview.model.ReviewTemplate;
import com.example.performancereview.service.ReviewTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@CrossOrigin(origins = "*")
public class ReviewTemplateController {

    private final ReviewTemplateService reviewTemplateService;

    public ReviewTemplateController(ReviewTemplateService reviewTemplateService) {
        this.reviewTemplateService = reviewTemplateService;
    }

    @PostMapping
    public ResponseEntity<ReviewTemplate> addTemplate(@RequestBody ReviewTemplate template) {
        if (template.getTitle() == null || template.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        ReviewTemplate savedTemplate = reviewTemplateService.addReviewTemplate(template);
        return ResponseEntity.ok(savedTemplate);
    }

    @GetMapping
    public List<ReviewTemplate> getAllTemplates() {
        return reviewTemplateService.getAllTemplates();
    }
}
