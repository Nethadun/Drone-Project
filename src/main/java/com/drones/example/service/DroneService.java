package com.drones.example.service;

import com.drones.example.dto.request.DroneDTO;
import com.drones.example.dto.response.ResponseDTO;

public interface DroneService {
    /**
     * This method for save drone details
     * @param droneDTO
     * @return ResponseDTO
     */
    public ResponseDTO save(DroneDTO droneDTO);
    /**
     * This method for checking available drones for loading
     * @return ResponseDTO
     */
    public ResponseDTO checkingAvailableDronesForLoading();
    /**
     * This method for checking given drone battery level
     * @return ResponseDTO
     */
    public ResponseDTO checkDroneBatteryLevel(Long droneId);
}
