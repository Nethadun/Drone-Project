package com.drones.example.service;

import com.drones.example.dto.request.MedicationDTO;
import com.drones.example.dto.response.ResponseDTO;

public interface MedicationService {
    public ResponseDTO save(MedicationDTO medicationDTO);
}
