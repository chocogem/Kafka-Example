package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseData {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Received_Time")
    private String receivedTime;
}
