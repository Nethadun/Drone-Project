package com.drones.example.model;

import com.drones.example.dto.enums.Model;
import com.drones.example.dto.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Size(max = 100, message = "type need to have minimum 100 characters")
    private Long serial;
    @Enumerated
    private Model model;
    @Size(max = 500, message = "type need to have minimum weight limit 500 characters")
    @Column(name = "weight_limit")
    private Integer weightLimit;
    @Column(name = "battery_capacity")
    private Double batteryCapacity;
    @Enumerated
    private State state;
}
