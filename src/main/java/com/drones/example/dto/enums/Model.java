package com.drones.example.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Model {
    LIGHTWEIGHT("(Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");
    public String model;
}
