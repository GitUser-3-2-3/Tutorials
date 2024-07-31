package io.parth.springbootstarter.topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.parth.springbootstarter.CommonController.*;

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
            return new ResponseEntity<>(allTopics, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allTopics, HttpStatus.OK);
        }
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<Optional<Topic>> getTopic(
            @PathVariable String topicId
    ) {
        Optional<Topic> topic = service.getTopic(topicId);

        if (topic.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(topic, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addTopic(
            @RequestBody Topic topic
    ) {
        Boolean isAdded = service.addTopic(topic);
        return getMapPostEntity(isAdded);
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<Map<String, String>> updateTopic(
            @RequestBody Topic topic, @PathVariable String topicId
    ) {
        Boolean isUpdated = service.updateTopic(topic, topicId);
        return getMapUpdateEntity(isUpdated);
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Map<String, String>> updateTopic(
            @PathVariable String topicId
    ) {
        Boolean isDeleted = service.deleteTopic(topicId);
        return getMapDeleteEntity(isDeleted);
    }
}