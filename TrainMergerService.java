package com.example.geektrust.service;

import com.example.geektrust.model.Bogie;
import com.example.geektrust.model.Train;
import com.example.geektrust.util.StationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainMergerService {

    private static final int HYDERABAD_DISTANCE_TRAIN_A = 1200; // From Chennai for Train A
    private static final int HYDERABAD_DISTANCE_TRAIN_B = 2000; // From Trivandrum for Train B

    //  Distance from HYDERABAD for sorting merged bogies
    private static final Map<String, Integer> stationDistanceFromHYB = new HashMap<>();
    static {
        stationDistanceFromHYB.put("GHY", 800);
        stationDistanceFromHYB.put("NJP", 700);
        stationDistanceFromHYB.put("PTA", 600);
        stationDistanceFromHYB.put("NDL", 550);
        stationDistanceFromHYB.put("AGA", 500);
        stationDistanceFromHYB.put("BPL", 450);
        stationDistanceFromHYB.put("ITJ", 400);
        stationDistanceFromHYB.put("NGP", 350);
        stationDistanceFromHYB.put("HBJ", 200);
        stationDistanceFromHYB.put("HYB", 0);
    }

    public void processTrains(String trainAInput, String trainBInput) {
        // 1. Parse Input
        Train trainA = parseTrainInput(trainAInput, "TRAIN_A", "CHN");
        Train trainB = parseTrainInput(trainBInput, "TRAIN_B", "TVC");

        // Remove HYB from Train B if Train A has HYB
        boolean trainAHasHYB = trainA.getBogies().stream()
                .anyMatch(b -> b.getDestinationStationCode().equals("HYB"));

        if (trainAHasHYB) {
            trainB.getBogies().removeIf(b -> b.getDestinationStationCode().equals("HYB"));
        }


        // 2. Determine Arrival Bogies for Train A
        List<Bogie> arrivalBogiesA = getArrivalBogies(trainA, HYDERABAD_DISTANCE_TRAIN_A);
        System.out.println("ARRIVAL " + trainA.getName() + " " + formatBogieList(arrivalBogiesA));

        // 3. Determine Arrival Bogies for Train B
        List<Bogie> arrivalBogiesB = getArrivalBogies(trainB, HYDERABAD_DISTANCE_TRAIN_B);
        System.out.println("ARRIVAL " + trainB.getName() + " " + formatBogieList(arrivalBogiesB));

        List<Bogie> continuingFromB = arrivalBogiesB.stream()
                .filter(b -> !"ENGINE".equals(b.getDestinationStationCode()) && !"HYB".equals(b.getDestinationStationCode()))
                .collect(Collectors.toList());

        List<Bogie> continuingFromA = arrivalBogiesA.stream()
                .filter(b -> !"ENGINE".equals(b.getDestinationStationCode()) && !"HYB".equals(b.getDestinationStationCode()))
                .collect(Collectors.toList());



        List<Bogie> mergedBogies = new ArrayList<>();
        mergedBogies.addAll(continuingFromB);
        mergedBogies.addAll(continuingFromA);

        //  Sort by distance from HYDERABAD (descending)
        mergedBogies.sort((b1, b2) -> {
            int d1 = getDistanceFromHyderabad(b1.getDestinationStationCode());
            int d2 = getDistanceFromHyderabad(b2.getDestinationStationCode());
            return Integer.compare(d2, d1); // Descending order
        });

        // 5. Departure Output
        if (mergedBogies.isEmpty()) {
            System.out.println("DEPARTURE TRAIN_AB JOURNEY_ENDED");
        } else {
            System.out.println("DEPARTURE TRAIN_AB ENGINE ENGINE " + formatBogieList(mergedBogies));
        }
    }

    private Train parseTrainInput(String input, String trainName, String originStationCode) {
        String[] parts = input.split(" ");
        Train train = new Train(trainName, originStationCode);
        for (int i = 2; i < parts.length; i++) {
            train.addBogie(new Bogie(parts[i]));
        }
        return train;
    }

    private List<Bogie> getArrivalBogies(Train train, int hyderabadDistance) {
        List<Bogie> continuingBogies = new ArrayList<>();
        continuingBogies.add(new Bogie("ENGINE"));

        for (Bogie bogie : train.getBogies()) {
            String destination = bogie.getDestinationStationCode();
            int destinationDistance = -1;

            if (train.getName().equals("TRAIN_A")) {
                destinationDistance = StationData.getTrainARouteDistance(destination);
            } else if (train.getName().equals("TRAIN_B")) {
                destinationDistance = StationData.getTrainBRouteDistance(destination);
            }

            if (destinationDistance != -1 && destinationDistance >= hyderabadDistance) {
                continuingBogies.add(bogie);
            }
        }
        return continuingBogies;
    }

    private String formatBogieList(List<Bogie> bogies) {
        return bogies.stream()
                .map(Bogie::getDestinationStationCode)
                .collect(Collectors.joining(" "));
    }

    private int getDistanceFromHyderabad(String station) {
        return stationDistanceFromHYB.getOrDefault(station, -1);
    }
}
