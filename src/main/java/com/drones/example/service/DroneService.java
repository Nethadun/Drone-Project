package com.drones.example.service;

import com.drones.example.dto.request.DroneDTO;
import com.drones.example.dto.response.ResponseDTO;

public interface DroneService {

    public ResponseDTO save(DroneDTO droneDTO);
}
