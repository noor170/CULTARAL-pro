package com.bangla.lms.repository;

import com.bangla.lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByLevel(String level);

    List<Course> findByPrice(BigDecimal price);
}
