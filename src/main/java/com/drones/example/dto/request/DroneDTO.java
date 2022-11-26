package com.drones.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DroneDTO {
    private Long serial;
    private String model;
    private Integer weightLimit;
    private Double batteryCapacity;
    private String state;
}
