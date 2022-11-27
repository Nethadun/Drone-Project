package com.drones.example.controller;

import com.drones.example.dto.request.DroneMedicationDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.service.DroneMedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispatch")
@Slf4j
public class DispatchController {

    @Autowired
    private DroneMedicationService droneMedicationService;

    /**
     * This method for added medications for each drones
     * @param droneMedicationDTO
     * @return ResponseDTO
     */
    @PostMapping("/save/medication/list")
    public ResponseDTO AddedMedicationForDrones(@RequestBody DroneMedicationDTO droneMedicationDTO){
        log.info("DroneMedicationService.AddedMedicationForDrones method accessed");
        return droneMedicationService.addedMedicationForDrones(droneMedicationDTO);
    }

    /**
     * This method for load drone with medication items
     * @param droneId
     * @return ResponseDTO
     */
    @GetMapping("/load/drone-medications")
    public ResponseDTO loadDroneWithMedication(@RequestParam Long droneId){
        log.info("DroneMedicationService.loadDroneWithMedication method accessed");
        return droneMedicationService.loadDroneWithMedication(droneId);
    }
}
