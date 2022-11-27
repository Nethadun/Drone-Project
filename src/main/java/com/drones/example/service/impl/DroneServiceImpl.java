package com.drones.example.service.impl;

import com.drones.example.dto.enums.Model;
import com.drones.example.dto.enums.State;
import com.drones.example.dto.request.DroneDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.model.Drone;
import com.drones.example.repository.DroneRepository;
import com.drones.example.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * This method for checking available drones for loading
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO checkingAvailableDronesForLoading() {
        log.info("DroneMedicationServiceImpl.validateDrone method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Drone> all = droneRepository.findAll();
            List<Drone> collect = all.stream().filter(drone -> drone.getState().equals(State.LOADING)).collect(Collectors.toList());
            if (!collect.isEmpty()){
                responseDTO.setPayload(collect);
                responseDTO.setHttpStatus(HttpStatus.ACCEPTED.value());
                responseDTO.setMessage("Drone battery stage is : "+ State.LOADING);
            }else {
                responseDTO.setHttpStatus(HttpStatus.OK.value());
                responseDTO.setMessage("No drones found");
            }
        }catch (Exception e){
            log.error("Error in DroneMedicationServiceImpl.validateDrone method : {}",e.getMessage());
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    /**
     * This method for checking given drone battery level
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO checkDroneBatteryLevel(Long droneId) {
        log.info("DroneMedicationServiceImpl.checkDroneBatteryLevel method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<Drone> byId = droneRepository.findById(droneId);
            if (byId.isPresent()){
                Drone drone = byId.get();
                responseDTO.setHttpStatus(HttpStatus.OK.value());
                responseDTO.setPayload(drone.getState());
                responseDTO.setMessage("Record found");
            }else {
                responseDTO.setHttpStatus(HttpStatus.NOT_FOUND.value());
                responseDTO.setMessage("Drone Not Found");
            }
        }catch (Exception e){
            log.error("Error in DroneMedicationServiceImpl.validateDrone method : {}",e.getMessage());
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
        drone.setModel(Model.valueOf(droneDTO.getModel()));
        drone.setState(State.valueOf(droneDTO.getState()));
        drone.setSerial(droneDTO.getSerial());
        drone.setWeightLimit(droneDTO.getWeightLimit());
        drone.setBatteryCapacity(droneDTO.getBatteryCapacity());
        return Optional.of(drone);
    }
}
