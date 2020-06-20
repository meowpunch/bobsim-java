package com.bobsim.server.model;

import lombok.Data;

@Data

public class Device {

    private String uniqueId;
    
    private String manufacturer;
    
    private String brand;

    private String carrier;

    private Integer buildNumber;

    private Double applicationVersion;

    private String userAgent;

    private Boolean isTablet;
}