package com.drones.example.dto.request;

import com.drones.example.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DroneMedicationDTO {
    private Long droneId;
    private List<Long> medicationList;
}
