package com.drones.example.service;

import com.drones.example.dto.request.MedicationDTO;
import com.drones.example.dto.response.ResponseDTO;

public interface MedicationService {

    /**
     * This method for medication save details
     * @param medicationDTO
     * @return ResponseDTO
     */
    public ResponseDTO save(MedicationDTO medicationDTO);
}
