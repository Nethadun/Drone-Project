package com.drones.example.service.impl;

import com.drones.example.dto.response.ResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DroneServiceImplTest {

    @Test
    @DisplayName("Should return the drone battery level when the drone is found")
    void checkDroneBatteryLevelWhenDroneIsFound() {
        DroneServiceImpl droneService = new DroneServiceImpl();
        ResponseDTO responseDTO = droneService.checkDroneBatteryLevel(1L);
        assertEquals(HttpStatus.OK.value(), responseDTO.getHttpStatus());
        assertEquals("Record found", responseDTO.getMessage());
    }

    @Test
    @DisplayName("Should return a message when the drone is not found")
    void checkDroneBatteryLevelWhenDroneIsNotFoundThenReturnMessage() {
        DroneServiceImpl droneService = new DroneServiceImpl();
        ResponseDTO responseDTO = droneService.checkDroneBatteryLevel(1L);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseDTO.getHttpStatus());
        assertEquals("Drone Not Found", responseDTO.getMessage());
    }

    @Test
    @DisplayName("Should return a list of drones when there are drones with loading state")
    void
    checkingAvailableDronesForLoadingWhenThereAreDronesWithLoadingStateThenReturnListOfDrones() {
        DroneServiceImpl droneService = new DroneServiceImpl();
        ResponseDTO responseDTO = droneService.checkingAvailableDronesForLoading();
        assertEquals(HttpStatus.ACCEPTED.value(), responseDTO.getHttpStatus());
    }

    @Test
    @DisplayName("Should return an empty list when there are no drones with loading state")
    void
    checkingAvailableDronesForLoadingWhenThereAreNoDronesWithLoadingStateThenReturnEmptyList() {
        DroneServiceImpl droneService = new DroneServiceImpl();
        ResponseDTO responseDTO = droneService.checkingAvailableDronesForLoading();
        assertEquals(HttpStatus.OK.value(), responseDTO.getHttpStatus());
        assertEquals("No drones found", responseDTO.getMessage());
    }

    @Test
    void checkingAvailableDronesForLoading() {
    }

    @Test
    void checkDroneBatteryLevel() {
    }
}