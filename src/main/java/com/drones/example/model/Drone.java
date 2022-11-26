package com.drones.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drones")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serial")
    private Long serial;
    @Column(name = "model")
    private String model;
    @Column(name = "weight_limit")
    private Integer weightLimit;
    @Column(name = "battery_capacity")
    private Double batteryCapacity;
    @Column(name = "state")
    private String state;
}
