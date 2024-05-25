package io.parth.springbootstarter.course;

import io.parth.springbootstarter.topic.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.parth.springbootstarter.CommonController.*;

@RestController
@RequestMapping("/topics/{topicId}/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(
            @PathVariable String topicId
    ) {
        List<Course> allCourses = service.getAllCourses(topicId);

        if (allCourses.isEmpty()) {
            return new ResponseEntity<>(allCourses, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allCourses, HttpStatus.OK);
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Optional<Course>> getCourse(
            @PathVariable String topicId,
            @PathVariable String courseId
    ) {
        Optional<Course> topic = service.getCourse(topicId, courseId);

        if (topic.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(topic, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addCourse(
            @RequestBody Course course,
            @PathVariable String topicId
    ) {
        course.setTopic(new Topic(topicId, "", ""));

        Boolean isAdded = service.addCourse(course);
        return getMapPostEntity(isAdded);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Map<String, String>> updateCourse(
            @RequestBody Course course,
            @PathVariable String courseId,
            @PathVariable String topicId
    ) {
        course.setTopic(new Topic(topicId, "", ""));

        Boolean isUpdated = service.updateCourse(course, courseId);
        return getMapUpdateEntity(isUpdated);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Map<String, String>> deleteCourse(
            @PathVariable String topicId,
            @PathVariable String courseId
    ) {
        Boolean isDeleted = service.deleteCourse(topicId, courseId);
        return getMapDeleteEntity(isDeleted);
    }
}
