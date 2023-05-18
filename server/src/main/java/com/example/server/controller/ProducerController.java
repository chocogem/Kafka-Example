package com.example.server.controller;


import com.example.server.entity.ResponseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.server.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.server.entity.RequestData;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@RestController
public class ProducerController {
    private final ProducerService producer;
    private static final Logger logger = LogManager.getFormatterLogger(ProducerController.class);

    @Autowired
    public ProducerController(ProducerService producer) {
        this.producer = producer;
    }
    @PostMapping("/publish")
    public ResponseData publish(@RequestBody RequestData requestData) throws JsonProcessingException {

        logger.info("Request %s",requestData.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(new Date()); //format 2022-01-29T03:23:511Z
        requestData.setReceivedTime(datetime);
        ResponseData responseData = new ResponseData();
        responseData.setReceivedTime(datetime);
        this.producer.sendMessage(requestData,new ProducerListener<Object, RequestData>() {

            @Override
            public void onError(ProducerRecord<Object, RequestData> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                responseData.setCode("FAILED");
                logger.info("Unable to Send Message ");
            }


        });
        if(!"FAILED".equalsIgnoreCase(responseData.getCode())){
            responseData.setCode("OK");
            logger.info("Sent Message Success");
        }
        logger.info("Response %s",responseData.toString());
        return responseData;
    }
}
