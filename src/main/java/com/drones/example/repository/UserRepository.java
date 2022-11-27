package com.drones.example.repository;

import com.drones.example.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Integer> {

    DAOUser findByUsername(String username);

}
