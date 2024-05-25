package io.parth.springbootstarter.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllTopics() {
        List<Course> allCours = service.getAllTopics();

        if (allCours.isEmpty()) {
            return new ResponseEntity<>(allCours, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allCours, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> getTopic(
            @PathVariable String id
    ) {
        Optional<Course> topic = service.getTopic(id);

        if (topic.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(topic, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addTopic(
            @RequestBody Course course
    ) {
        Boolean isAdded = service.addTopic(course);
        Map<String, String> response = new HashMap<>();

        if (!isAdded) {
            response.put("message", "Couldn't Add Course");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("message", "Course Added");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTopic(
            @RequestBody Course course,
            @PathVariable String id
    ) {
        Boolean isUpdated = service.updateTopic(course, id);
        Map<String, String> response = new HashMap<>();

        if (!isUpdated) {
            response.put("message", "Couldn't Update Course");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("message", "Course Updated");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTopic(
            @PathVariable String id
    ) {
        Boolean isDeleted = service.deleteTopic(id);
        Map<String, String> response = new HashMap<>();

        if (!isDeleted) {
            response.put("message", "Couldn't Delete Course");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("message", "Course Deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
