package com.drones.example.controller;

import com.drones.example.dto.request.MedicationDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.service.MedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medication")
@Slf4j
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    /**
     * This method for medication save details
     * @param medicationDTO
     * @return ResponseDTO
     */
    @PostMapping("/save")
    public ResponseDTO save(@ModelAttribute MedicationDTO medicationDTO){
        log.info("MedicationController.save method accessed");
        return medicationService.save(medicationDTO);
    }
}
