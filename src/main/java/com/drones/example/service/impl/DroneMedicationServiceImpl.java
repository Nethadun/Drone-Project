package com.drones.example.service.impl;
import com.drones.example.dto.request.DroneMedicationDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.model.Drone;
import com.drones.example.model.DroneMedication;
import com.drones.example.model.Medication;
import com.drones.example.repository.DroneMedicationRepository;
import com.drones.example.repository.DroneRepository;
import com.drones.example.repository.MedicationRepository;
import com.drones.example.service.DroneMedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class DroneMedicationServiceImpl implements DroneMedicationService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private DroneMedicationRepository droneMedicationRepository;
    /**
     * This method for added medications for each drones
     * @param droneMedicationDTO
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO addedMedicationForDrones(DroneMedicationDTO droneMedicationDTO) {
        log.info("DroneMedicationServiceImpl.addedMedicationForDrones method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<DroneMedication> medicationList = entitySet(droneMedicationDTO);
            if (!medicationList.isEmpty()){
                List<DroneMedication> medicationList1 = droneMedicationRepository.saveAllAndFlush(medicationList);
                responseDTO.setHttpStatus(HttpStatus.CREATED.value());
                responseDTO.setMessage("Record save success");
                responseDTO.setPayload(medicationList1);
            }else {
                responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Failed to save");
            }
        }catch (Exception e){
            log.error("Error in DroneMedicationServiceImpl.addedMedicationForDrones method : {}",e.getMessage());
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    /**
     * This method for load drone with medication items
     * @param droneId
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO loadDroneWithMedication(Long droneId) {
        log.info("DroneMedicationServiceImpl.loadDroneWithMedication method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<DroneMedication> allByDrone = droneMedicationRepository.findAll();
            List<DroneMedication> collect = allByDrone.stream().filter(droneMedication -> droneMedication.getDrone().getId().equals(droneId)).collect(Collectors.toList());
            if (!collect.isEmpty()){
                responseDTO.setHttpStatus(HttpStatus.FOUND.value());
                responseDTO.setMessage("Record found");
                responseDTO.setPayload(collect);
            }else {
                responseDTO.setHttpStatus(HttpStatus.NOT_FOUND.value());
                responseDTO.setMessage("Record not found");
            }
        }catch (Exception e){
            log.error("Error in DroneMedicationServiceImpl.loadDroneWithMedication method : {}",e.getMessage());
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    /**
     * This method for load all drone with medication items
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO loadAllDroneWithMedication() {
        log.info("DroneMedicationServiceImpl.loadAllDroneWithMedication method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<DroneMedication> allByDrone = droneMedicationRepository.findAll();
            if (!allByDrone.isEmpty()){
                responseDTO.setHttpStatus(HttpStatus.FOUND.value());
                responseDTO.setMessage("Record found");
                responseDTO.setPayload(allByDrone);
            }else {
                responseDTO.setHttpStatus(HttpStatus.NOT_FOUND.value());
                responseDTO.setMessage("Record not found");
            }
        }catch (Exception e){
            log.error("Error in DroneMedicationServiceImpl.loadAllDroneWithMedication method : {}",e.getMessage());
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

//    /**
//     * This method for validate drone battery level
//     * @param droneId
//     * @return ResponseDTO
//     */
//    private ResponseDTO validateDrone(Long droneId) {
//        log.info("DroneMedicationServiceImpl.validateDrone method accessed");
//        ResponseDTO responseDTO = new ResponseDTO();
//        try {
//            Optional<Drone> byId = droneRepository.findById(droneId);
//            if (byId.isPresent()){
//                Drone drone = byId.get();
//                if (drone.getState().equals(State.LOADING)){
//                    responseDTO.setHttpStatus(HttpStatus.NOT_ACCEPTABLE.value());
//                    responseDTO.setMessage("Drone battery stage is : "+ drone.getState());
//                }else {
//                    responseDTO.setHttpStatus(HttpStatus.OK.value());
//                    responseDTO.setMessage("Drone ready to use");
//                }
//            }else {
//                responseDTO.setHttpStatus(HttpStatus.NOT_FOUND.value());
//                responseDTO.setMessage("Drone Not Found");
//            }
//        }catch (Exception e){
//            log.error("Error in DroneMedicationServiceImpl.validateDrone method : {}",e.getMessage());
//            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
//            responseDTO.setMessage(e.getMessage());
//        }
//
//        return responseDTO;
//    }
    /**
     * This method for map dto to model
     * @param droneMedicationDTO
     * @return Optional<Drone>
     */
    private List<DroneMedication> entitySet(DroneMedicationDTO droneMedicationDTO){
        log.info("DroneMedicationServiceImpl.entitySet method accessed");
        List<Medication> allById = medicationRepository.findAllById(droneMedicationDTO.getMedicationList());
        List<DroneMedication> medicationList=  new ArrayList<>();
        allById.forEach(medication -> {
            DroneMedication droneMedication = new DroneMedication();
            droneMedication.setMedication(medication);
            Optional<Drone> byId = droneRepository.findById(droneMedicationDTO.getDroneId());
            if (byId.isPresent()){
                Drone drone = byId.get();
                droneMedication.setDrone(drone);
            }
            medicationList.add(droneMedication);

        });
        return medicationList;
    }
}
