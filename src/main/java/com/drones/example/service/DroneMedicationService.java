package com.drones.example.service;

import com.drones.example.dto.request.DroneMedicationDTO;
import com.drones.example.dto.response.ResponseDTO;

public interface DroneMedicationService {
    /**
     * This method for added medications for each drones
     * @param droneMedicationDTO
     * @return ResponseDTO
     */
    public ResponseDTO addedMedicationForDrones(DroneMedicationDTO droneMedicationDTO);
    public ResponseDTO loadDroneWithMedication(Long droneId);
}
