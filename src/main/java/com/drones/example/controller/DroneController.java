package com.drones.example.controller;

import com.drones.example.dto.request.DroneDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drone")
@Slf4j
public class DroneController {

    @Autowired
    private DroneService droneService;

    /**
     * This method for save drone details
     * @param droneDTO
     * @return ResponseDTO
     */
    @PostMapping("/save")
    public ResponseDTO save(@RequestBody DroneDTO droneDTO){
        log.info("DroneController.save method accessed");
        return droneService.save(droneDTO);
    }


}
