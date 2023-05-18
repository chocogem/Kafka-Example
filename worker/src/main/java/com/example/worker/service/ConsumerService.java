package com.example.worker.service;

import com.example.worker.entity.RequestData;
import com.example.worker.repository.ConsumerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;
    private static final Logger logger = LogManager.getFormatterLogger(ConsumerService.class);


    @KafkaListener(topics = "myTopic1", groupId = "myGroup1")
    public void consume0(@Payload RequestData message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {
//        logger.info("Consumer 0 Partition %d : %s",partition , message.toString());
        logger.info("#1,%s++:%s,%s",message.getSender(),message.getMsg() , message.getReceivedTime());
        consumerRepository.save(message);
    }
    @KafkaListener(topics = "myTopic1", groupId = "myGroup1")
    public void consume1(@Payload RequestData message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {
//        logger.info("Consumer 1 Partition %d : %s",partition , message.toString());
        logger.info("#2,%s++:%s,%s",message.getSender(),message.getMsg() , message.getReceivedTime());
        consumerRepository.save(message);
    }
    @KafkaListener(topics = "myTopic1", groupId = "myGroup1")
    public void consume2(@Payload RequestData message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {
//        logger.info("Consumer 2 Partition %d : %s",partition , message.toString());
        logger.info("#3,%s++:%s,%s",message.getSender(),message.getMsg() , message.getReceivedTime());
        consumerRepository.save(message);
    }

    public List<RequestData> findAllMessage() {
        return consumerRepository.findAll();
    }
    public Optional<RequestData> findByMsgId(Integer msgId) {
        return consumerRepository.findById(msgId);
    }
    public void deleteByMsgId(Integer msgId) {
        consumerRepository.deleteById(msgId);
    }
    public void deleteAll() {
        consumerRepository.deleteAll();
    }
}
