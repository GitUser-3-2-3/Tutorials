package io.parth.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("spring", "spring framework", "spring framework description"),
            new Topic("java", "java framework", "java description"),
            new Topic("javascript", "angular", "javascript description")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String id) {
        return topics.stream()
                .filter(topic -> topic.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Boolean addTopic(Topic topic) {
        return topics.add(topic);
    }

    public Boolean updateTopic(Topic updatedTopic, String id) {
        return topics.stream()
                .filter(topic -> topic.getId().equals(id))
                .peek(topic -> {
                    topic.setName(updatedTopic.getName());
                    topic.setDescription(updatedTopic.getDescription());
                })
                .findFirst()
                .isPresent();
    }
}
