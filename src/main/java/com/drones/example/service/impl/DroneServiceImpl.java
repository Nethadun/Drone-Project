package com.drones.example.service.impl;

import com.drones.example.dto.request.DroneDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.model.Drone;
import com.drones.example.repository.DroneRepository;
import com.drones.example.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    /**
     * This method for save drone details
     * @param droneDTO
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO save(DroneDTO droneDTO) {
        log.info("DroneServiceImpl.save method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<Drone> drone = entitySet(droneDTO);
            if(drone.isPresent()){
                Drone drone1 = drone.get();
                Drone save = droneRepository.save(drone1);
                responseDTO.setHttpStatus(HttpStatus.CREATED.value());
                responseDTO.setMessage("Drone record save success");
                responseDTO.setPayload(save);
            }else {
                responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Drone record failed to save");
            }
        }catch (Exception e){
            log.error("Error in DroneServiceImpl.save method : {}", e.getMessage());
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    /**
     * This method for map dto to model
     * @param droneDTO
     * @return Optional<Drone>
     */
    private Optional<Drone> entitySet(DroneDTO droneDTO){
        log.info("DroneServiceImpl.entitySet method accessed");
        Drone drone = new Drone();
        drone.setModel(droneDTO.getModel());
        drone.setState(droneDTO.getState());
        drone.setSerial(droneDTO.getSerial());
        drone.setWeightLimit(droneDTO.getWeightLimit());
        drone.setBatteryCapacity(droneDTO.getBatteryCapacity());
        return Optional.of(drone);
    }
}
