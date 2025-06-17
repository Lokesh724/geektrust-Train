#!/bin/bash

mvn clean install -DskipTests assembly:single -q
java -jar target/geektrust.jar sample_input/input1.txt
import java.util.*;

public class TrainDistanceMaps {

    public static Map<String, Integer> getTrainADistanceMap() {
        Map<String, Integer> trainADistance = new HashMap<>();

        // Train A: Chennai (CHN) to New Delhi (NDL)
        trainADistance.put("CHN", 0);
        trainADistance.put("SLM", 350);
        trainADistance.put("BLR", 550);
        trainADistance.put("KRN", 900);
        trainADistance.put("HYB", 1200);
        trainADistance.put("NGP", 1600);
        trainADistance.put("ITJ", 1900);
        trainADistance.put("BPL", 2000);
        trainADistance.put("AGA", 2500);
        trainADistance.put("NDL", 2700);

        return trainADistance;
    }

    public static void main(String[] args) {
        Map<String, Integer> trainAMap = getTrainADistanceMap();

        // Print to confirm
        for (Map.Entry<String, Integer> entry : trainAMap.entrySet()) {
            System.out.println("Station: " + entry.getKey() + ", Distance: " + entry.getValue());
        }
    }
}
