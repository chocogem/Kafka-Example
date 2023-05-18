package com.example.worker.repository;

import com.example.worker.entity.RequestData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<RequestData, Integer> {
}
