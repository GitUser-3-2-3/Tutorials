package io.parth.springbootstarter.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();

        topicRepository.findAll()
                .forEach(topics::add);
        return topics;
    }

    public Optional<Topic> getTopic(String id) {
        return topicRepository.findById(id);
    }

    public Boolean addTopic(Topic topic) {
        if (topic != null) {
            topicRepository.save(topic);
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateTopic(Topic updatedTopic, String id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {
            topicRepository.save(updatedTopic);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteTopic(String id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {
            topicRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
