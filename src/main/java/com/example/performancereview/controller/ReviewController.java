package com.example.performancereview.controller;

import com.example.performancereview.model.Employee;
import com.example.performancereview.model.Review;
import com.example.performancereview.model.ReviewTemplate;
import com.example.performancereview.repository.EmployeeRepository;
import com.example.performancereview.repository.ReviewTemplateRepository;
import com.example.performancereview.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;
    private final EmployeeRepository employeeRepository;
    private final ReviewTemplateRepository reviewTemplateRepository;

    public ReviewController(ReviewService reviewService,
                            EmployeeRepository employeeRepository,
                            ReviewTemplateRepository reviewTemplateRepository) {
        this.reviewService = reviewService;
        this.employeeRepository = employeeRepository;
        this.reviewTemplateRepository = reviewTemplateRepository;
    }

    @PostMapping
    public ResponseEntity<Review> submitReview(@RequestBody Review review) {
        // Validate employee
        if (review.getEmployee() == null || review.getEmployee().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Employee> employeeOpt = employeeRepository.findById(review.getEmployee().getId());
        if (employeeOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Validate reviewTemplate
        if (review.getReviewTemplate() == null || review.getReviewTemplate().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<ReviewTemplate> templateOpt = reviewTemplateRepository.findById(review.getReviewTemplate().getId());
        if (templateOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Validate score (optional: you can add min and max score checks)
        if (review.getScore() == null || review.getScore() < 0) {
            return ResponseEntity.badRequest().build();
        }

        // Set actual entities before saving (to avoid detached entity errors)
        review.setEmployee(employeeOpt.get());
        review.setReviewTemplate(templateOpt.get());

        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Review>> getReviewsForEmployee(@PathVariable Long id) {
        List<Review> reviews = reviewService.getReviewsByEmployeeId(id);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}
