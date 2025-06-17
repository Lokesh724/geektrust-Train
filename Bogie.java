package com.example.geektrust.model;

/**
 * Represents a single bogie (coach) of a train.
 * Each bogie has a destination station code.
 */
public class Bogie {
    private String destinationStationCode;

    /**
     * Constructs a new Bogie with the given destination station code.
     *
     * @param destinationStationCode The station code where this bogie detaches.
     */
    public Bogie(String destinationStationCode) {
        this.destinationStationCode = destinationStationCode;
    }

    /**
     * Returns the destination station code for this bogie.
     *
     * @return The destination station code.
     */
    public String getDestinationStationCode() {
        return destinationStationCode;
    }

    // Optional: Override equals and hashCode for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bogie bogie = (Bogie) o;
        return destinationStationCode.equals(bogie.destinationStationCode);
    }

    @Override
    public int hashCode() {
        return destinationStationCode.hashCode();
    }

    @Override
    public String toString() {
        return "Bogie{" +
                "destinationStationCode='" + destinationStationCode + '\'' +
                '}';
    }
}
