package com.drones.example.service.impl;

import com.drones.example.dto.request.MedicationDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.model.Medication;
import com.drones.example.repository.MedicationRepository;
import com.drones.example.service.MedicationService;
import com.drones.example.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;
    /**
     * This method for medication save details
     * @param medicationDTO
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO save(MedicationDTO medicationDTO) {
        log.info("MedicationServiceImpl.save method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<Medication> medication = entitySet(medicationDTO);
            if (medication.isPresent()){
                Medication medication1 = medication.get();
                Medication save = medicationRepository.save(medication1);
                responseDTO.setHttpStatus(HttpStatus.CREATED.value());
                responseDTO.setMessage("Medication record save success");
                responseDTO.setPayload(save);
            }else {
                responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Medication record failed to save");
            }
        }catch (Exception e){
            log.error("Error in MedicationServiceImpl.save method : {}", e.getMessage());
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    private Optional<Medication> entitySet(MedicationDTO medicationDTO){
        log.info("MedicationServiceImpl.entitySet method accessed");
        Medication medication = new Medication();
        try {
            medication.setCode(medicationDTO.getCode());
            medication.setName(medicationDTO.getName());
            medication.setImage(ImageUtil.compressImage(medicationDTO.getImage().getBytes()));
        } catch (IOException e) {
            log.error("Error in MedicationServiceImpl.entitySet method : {}", e.getMessage());
        }
        return Optional.of(medication);
    }
}
