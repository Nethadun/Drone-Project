package com.drones.example.service.impl;

import com.drones.example.dto.request.MedicationDTO;
import com.drones.example.dto.response.ResponseDTO;
import com.drones.example.model.Medication;
import com.drones.example.repository.MedicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest {

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationServiceImpl medicationService;

    @Test
    @DisplayName(
            "Should return a response with http status code 400 when the medication is not saved")
    void saveWhenMedicationIsNotSavedThenReturnResponseWithHttpStatusCode400() {
        MedicationDTO medicationDTO = new MedicationDTO();
        medicationDTO.setCode("123");
        medicationDTO.setName("Paracetamol");
        when(medicationRepository.save(any(Medication.class))).thenReturn(null);
        ResponseDTO responseDTO = medicationService.save(medicationDTO);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseDTO.getHttpStatus());
        assertEquals("Medication record failed to save", responseDTO.getMessage());
    }

    @Test
    @DisplayName("Should return a response with http status code 201 when the medication is saved")
    void saveWhenMedicationIsSavedThenReturnResponseWithHttpStatusCode201() {
        MedicationDTO medicationDTO = new MedicationDTO("name", "code", null);
        Medication medication = new Medication();
        medication.setId(1L);
        medication.setName("name");
        medication.setCode("code");
        when(medicationRepository.save(any(Medication.class))).thenReturn(medication);

        ResponseDTO responseDTO = medicationService.save(medicationDTO);

        assertEquals(HttpStatus.CREATED.value(), responseDTO.getHttpStatus());
        assertEquals("Medication record save success", responseDTO.getMessage());
        assertEquals(medication, responseDTO.getPayload());
    }
}