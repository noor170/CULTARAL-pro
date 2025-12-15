package com.bangla.lms.config;

import com.bangla.lms.entity.Course;
import com.bangla.lms.entity.Lesson;
import com.bangla.lms.repository.CourseRepository;
import com.bangla.lms.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    @Override
    public void run(String... args) {
        Course course1 = new Course();
        course1.setTitle("বাংলা বর্ণমালা - Bangla Alphabet");
        course1.setDescription("Learn the foundation of Bangla language with all vowels and consonants. Perfect for absolute beginners.");
        course1.setLevel("Beginner");
        course1.setThumbnailUrl("https://images.pexels.com/photos/8519791/pexels-photo-8519791.jpeg?auto=compress&cs=tinysrgb&w=800");
        course1.setPrice(BigDecimal.valueOf(0.0));
        course1 = courseRepository.save(course1);

        Course course2 = new Course();
        course2.setTitle("প্রাথমিক কথোপকথন - Basic Conversations");
        course2.setDescription("Master essential daily conversations in Bangla. Learn greetings, introductions, and common phrases.");
        course2.setLevel("Beginner");
        course2.setThumbnailUrl("https://images.pexels.com/photos/5212345/pexels-photo-5212345.jpeg?auto=compress&cs=tinysrgb&w=800");
        course2.setPrice(BigDecimal.valueOf(0.0));
        course2 = courseRepository.save(course2);

        Course course3 = new Course();
        course3.setTitle("বাংলা ব্যাকরণ - Bangla Grammar");
        course3.setDescription("Understand the structure of Bangla language with comprehensive grammar lessons.");
        course3.setLevel("Intermediate");
        course3.setThumbnailUrl("https://images.pexels.com/photos/4145153/pexels-photo-4145153.jpeg?auto=compress&cs=tinysrgb&w=800");
        course3.setPrice(BigDecimal.valueOf(29.99));
        course3 = courseRepository.save(course3);

        Lesson lesson1 = new Lesson();
        lesson1.setCourse(course1);
        lesson1.setTitle("স্বরবর্ণ - Vowels (অ, আ, ই, ঈ)");
          lesson2.setVideoUrl("https://www.youtube.com/embed/dQw4w9WgXcQ");
        // lesson1.setVideoUrl("bUtVkW7pAcs");
        lesson1.setContent("বাংলা ভাষায় ১১টি স্বরবর্ণ আছে: অ, আ, ই, ঈ, উ, ঊ, ঋ, এ, ঐ, ও, ঔ");
        lesson1.setLessonOrder(1);
        lessonRepository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setCourse(course1);
        lesson2.setTitle("ব্যঞ্জনবর্ণ - Consonants (ক-বর্গ)");
        lesson1.setVideoUrl("https://www.youtube.com/embed/dQw4w9WgXcQ");
        // lesson2.setVideoUrl("bUtVkW7pAcs");
        lesson2.setContent("বাংলা ভাষায় ৩৯টি ব্যঞ্জনবর্ণ আছে। ক-বর্গ: ক, খ, গ, ঘ, ঙ");
        lesson2.setLessonOrder(2);
        lessonRepository.save(lesson2);
    }
}
