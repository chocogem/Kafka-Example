package com.example.server.service;

import com.example.server.entity.RequestData;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {
    private static final String TOPIC = "myTopic1";
    @Autowired
    private KafkaTemplate<Object, RequestData> kafkaTemplate;

    public void sendMessage(RequestData message, ProducerListener<Object, RequestData> callback){
        kafkaTemplate.setProducerListener(callback);
        kafkaTemplate.send(TOPIC, message);
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }



}
