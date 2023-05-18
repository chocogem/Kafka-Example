package com.example.worker.controller;


import com.example.worker.entity.RequestData;
import com.example.worker.service.ConsumerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/msg")
public class ConsumerController {
    private final ConsumerService consumerService;
    private static final Logger logger = LogManager.getFormatterLogger(ConsumerController.class);

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping
    public List<RequestData> findAll() {
        return consumerService.findAllMessage();
    }

    @GetMapping("/{msgId}")
    public Optional<RequestData> findById(@PathVariable Integer msgId) {
        return consumerService.findByMsgId(msgId);
    }
    @DeleteMapping("/delete/{msgId}")
    public void deleteById(@PathVariable Integer msgId) {
         consumerService.deleteByMsgId(msgId);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        consumerService.deleteAll();
    }
}
