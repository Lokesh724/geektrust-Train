package com.example.geektrust.model; // Or com.example.geektrust

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // For stream operations later

public class Train {
    private String name; // e.g., "TRAIN_A", "TRAIN_B", "TRAIN_AB"
    private List<Bogie> bogies;
    private String originStationCode; // CHN for Train A, TVC for Train B

    public Train(String name, String originStationCode) {
        this.name = name;
        this.bogies = new ArrayList<>();
        this.originStationCode = originStationCode;
    }

    public String getName() {
        return name;
    }

    public String getOriginStationCode() {
        return originStationCode;
    }

    public List<Bogie> getBogies() {
        return bogies;
    }

    public void addBogie(Bogie bogie) {
        this.bogies.add(bogie);
    }

    // Helper method to get bogie names for output
    public String getBogieOrderString() {
        return "ENGINE " + bogies.stream()
                .map(Bogie::getDestinationStationCode)
                .collect(Collectors.joining(" "));
    }
}
