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

    public List<Course> getAllTopics() {
        List<Course> cours = new ArrayList<>();

        courseRepository.findAll()
                .forEach(cours::add);
        return cours;
    }

    public Optional<Course> getTopic(String id) {
        return courseRepository.findById(id);
    }

    public Boolean addTopic(Course course) {
        if (course != null) {
            courseRepository.save(course);
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateTopic(Course updatedCourse, String id) {
        Optional<Course> topic = courseRepository.findById(id);

        if (topic.isPresent()) {
            courseRepository.save(updatedCourse);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteTopic(String id) {
        Optional<Course> topic = courseRepository.findById(id);

        if (topic.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
