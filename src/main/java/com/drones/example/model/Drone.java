package com.drones.example.model;

import com.drones.example.dto.enums.Model;
import com.drones.example.dto.enums.State;
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
    private Long serial;
    @Enumerated
    private Model model;
    @Column(name = "weight_limit")
    private Integer weightLimit;
    @Column(name = "battery_capacity")
    private Double batteryCapacity;
    @Enumerated
    private State state;
}
