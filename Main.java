package com.example.geektrust;

import com.example.geektrust.service.TrainMergerService; // Import service class
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the input file path as a command line argument.");
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);

            List<String> inputCommands = new ArrayList<>();
            while (sc.hasNextLine()) {
                inputCommands.add(sc.nextLine());
            }
            sc.close();

            if (inputCommands.size() >= 2) {
                String trainAInput = inputCommands.get(0);
                String trainBInput = inputCommands.get(1);

                // Instantiate and call service
                TrainMergerService service = new TrainMergerService();
                service.processTrains(trainAInput, trainBInput);

            } else {
                System.out.println("Error: Expected two lines of input for TRAIN_A and TRAIN_B.");
            }

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
