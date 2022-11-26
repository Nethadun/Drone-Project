package com.drones.example.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum State {
    IDLE("(IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    HEAVYWEIGHT("RETURNING");
    public String state;

}
