package com.drones.example.service;

import com.drones.example.dto.enums.Model;
import com.drones.example.dto.enums.State;
import com.drones.example.model.Drone;
import com.drones.example.repository.DroneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DroneServiceImplTest {

    @Autowired
    private DroneRepository repository;

    @Test
    public void testSaveNewDrone() {
        Drone drone =new Drone(3L,121237L, Model.CRUISERWEIGHT,200,20.0, State.LOADING);
        repository.save(drone);
        assertNotNull(repository.findById(3L).get());
    }

    public void testCheckingAvailableDronesForLoading(){

    }
}
