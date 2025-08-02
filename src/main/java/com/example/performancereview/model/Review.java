package com.example.performancereview.model;


import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private ReviewTemplate reviewTemplate;

    private Integer score;
    private String feedback;

    // Constructors
    public Review() {}

    public Review(Employee employee, ReviewTemplate reviewTemplate, Integer score, String feedback) {
        this.employee = employee;
        this.reviewTemplate = reviewTemplate;
        this.score = score;
        this.feedback = feedback;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public ReviewTemplate getReviewTemplate() { return reviewTemplate; }
    public void setReviewTemplate(ReviewTemplate reviewTemplate) { this.reviewTemplate = reviewTemplate; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
