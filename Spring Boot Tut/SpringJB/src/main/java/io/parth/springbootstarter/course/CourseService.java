package io.parth.springbootstarter.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(String topicId) {
/*
        List<Course> course = new ArrayList<>();

        courseRepository.findByTopicId(topicId)
                .forEach(course::add);
        return course;
*/

        return new ArrayList<>(courseRepository.findByTopicId(topicId));
    }

    public Optional<Course> getCourse(String topicId, String courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        if (course.isPresent() &&
                course.get().getTopic().getId().equals(topicId)
        ) {
            return course;
        } else {
            return Optional.empty();
        }
    }

    public Boolean addCourse(Course course) {
        if (course != null) {
            courseRepository.save(course);
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateCourse(Course updatedCourse, String courseId) {
        Optional<Course> topic = courseRepository.findById(courseId);

        if (topic.isPresent()) {
            courseRepository.save(updatedCourse);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteCourse(String topicId, String courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        if (course.isPresent() &&
                course.get().getTopic().getId().equals(topicId)
        ) {
            courseRepository.deleteById(courseId);
            return true;
        } else {
            return false;
        }
    }
}
