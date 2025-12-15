package com.bangla.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(name = "video_id", nullable = true, length = 50)
    private String videoId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    // Compatibility methods for legacy field names used elsewhere (e.g. DataInitializer)
    // These delegate to the actual fields (videoId <-> videoUrl, orderIndex <-> lessonOrder)
    public void setVideoUrl(String videoUrl) {
        this.videoId = videoUrl;
    }

    public String getVideoUrl() {
        return this.videoId;
    }

    public void setLessonOrder(Integer lessonOrder) {
        this.orderIndex = lessonOrder;
    }

    public Integer getLessonOrder() {
        return this.orderIndex;
    }

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
