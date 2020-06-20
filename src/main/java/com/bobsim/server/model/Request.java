package com.bobsim.server.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data

public class Request {
    
    private String id;

    private LocalDateTime at;

    private Device device;

    public Request(Device device) {
        this.id = UUID.randomUUID().toString();
        this.at = LocalDateTime.now();
        this.device = device;
    }
}
