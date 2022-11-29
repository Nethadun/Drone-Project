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
     * @Author Nethadun
     */
    @PostMapping("/save")
    public ResponseDTO save(@RequestBody DroneDTO droneDTO){
        log.info("DroneController.save method accessed");
        return droneService.save(droneDTO);
    }

    /**
     * This method for checking available drones for loading
     * @return ResponseDTO
     * @Author Nethadun
     */
    @GetMapping("/drone-state/loading")
    public ResponseDTO checkingAvailableDronesForLoading(){
        log.info("DroneController.checkingAvailableDronesForLoading method accessed");
        return droneService.checkingAvailableDronesForLoading();
    }
    /**
     * This method for checking given drone battery level
     * @return ResponseDTO
     * @Author Nethadun
     */
    @GetMapping("/drone-state")
    public ResponseDTO checkDroneBatteryLevel(@RequestParam Long droneId){
        log.info("DroneController.checkingAvailableDronesForLoading method accessed");
        return droneService.checkDroneBatteryLevel(droneId);
    }


}
