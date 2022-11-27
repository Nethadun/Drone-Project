package com.drones.example.repository;

import com.drones.example.model.DroneMedication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {

    List<DroneMedication> findAllByDrone(Long id);

}
