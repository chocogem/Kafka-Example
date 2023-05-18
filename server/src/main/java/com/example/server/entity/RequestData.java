package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

@Data
public class RequestData {

    @Id
    @JsonProperty("Msg_id")
    private Integer msgId;
    @JsonProperty("Sender")
    @NotNull
    private String sender;
    @JsonProperty("Msg")
    @NotNull
    private String msg;
    private String receivedTime;

}
