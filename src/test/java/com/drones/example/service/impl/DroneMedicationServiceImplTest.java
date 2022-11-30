package com.drones.example.service.impl;

import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.model.Drone;
import com.drones.example.model.DroneMedication;
import com.drones.example.model.Medication;
import com.drones.example.repository.DroneMedicationRepository;
import com.drones.example.repository.DroneRepository;
import com.drones.example.repository.MedicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DroneMedicationServiceImpl")
class DroneMedicationServiceImplTest {

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private DroneMedicationRepository droneMedicationRepository;
    @InjectMocks
    private DroneMedicationServiceImpl droneMedicationService;

    @Test
    @DisplayName("Should return an empty list when the droneid is not found")
    void loadDroneWithMedicationWhenDroneIdIsNotFoundThenReturnEmptyList() {
        Long droneId = 1L;
        when(droneMedicationRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseDTO responseDTO = droneMedicationService.loadDroneWithMedication(droneId);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseDTO.getHttpStatus());
        assertEquals("Record not found", responseDTO.getMessage());
    }

    @Test
    @DisplayName("Should return a list of dronemedication when the droneid is found")
    void loadDroneWithMedicationWhenDroneIdIsFoundThenReturnListOfDroneMedication() {
        Long droneId = 1L;
        Drone drone = new Drone();
        drone.setId(droneId);
        Medication medication = new Medication();
        medication.setId(1L);
        DroneMedication droneMedication = new DroneMedication();
        droneMedication.setDrone(drone);
        droneMedication.setMedication(medication);
        List<DroneMedication> droneMedications = Collections.singletonList(droneMedication);
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        when(droneMedicationRepository.findAll()).thenReturn(droneMedications);

        ResponseDTO responseDTO = droneMedicationService.loadDroneWithMedication(droneId);

        assertEquals(HttpStatus.FOUND.value(), responseDTO.getHttpStatus());
        assertEquals("Record found", responseDTO.getMessage());
        assertEquals(droneMedications, responseDTO.getPayload());
    }
}