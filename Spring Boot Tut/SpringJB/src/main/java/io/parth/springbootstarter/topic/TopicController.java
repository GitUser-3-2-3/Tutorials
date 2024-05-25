package io.parth.springbootstarter.topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> allTopics = service.getAllTopics();

        if (allTopics.isEmpty()) {
            return new ResponseEntity<>(
                    allTopics, HttpStatus.NOT_FOUND
            );
        } else {
            return new ResponseEntity<>(
                    allTopics, HttpStatus.OK
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Topic>> getTopic(
            @PathVariable String id
    ) {
        Optional<Topic> topic = service.getTopic(id);

        if (topic.isEmpty()) {
            return new ResponseEntity<>(
                    null, HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                topic, HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addTopic(
            @RequestBody Topic topic
    ) {
        Boolean isAdded = service.addTopic(topic);
        Map<String, String> response = new HashMap<>();

        if (!isAdded) {
            response.put("message", "Couldn't Add Topic");
            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST
            );
        } else {
            response.put("message", "Topic Added");
            return new ResponseEntity<>(
                    response, HttpStatus.OK
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTopic(
            @RequestBody Topic topic,
            @PathVariable String id
    ) {
        Boolean isUpdated = service.updateTopic(topic, id);
        Map<String, String> response = new HashMap<>();

        if (!isUpdated) {
            response.put("message", "Couldn't Update Topic");
            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST
            );
        } else {
            response.put("message", "Topic Updated");
            return new ResponseEntity<>(
                    response, HttpStatus.OK
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTopic(
            @PathVariable String id
    ) {
        Boolean isDeleted = service.deleteTopic(id);
        Map<String, String> response = new HashMap<>();

        if (!isDeleted) {
            response.put("message", "Couldn't Delete Topic");
            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST
            );
        } else {
            response.put("message", "Topic Deleted");
            return new ResponseEntity<>(
                    response, HttpStatus.OK
            );
        }
    }
}
