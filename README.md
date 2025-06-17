# geektrust-Train
Solution for the GeekTrust Train Management Challenge – Java-based CLI program that simulates train bogie merging based on destination priorities.
# 🚆 GeekTrust Train Challenge

This is a solution to the **GeekTrust Train Management** problem. The program takes two trains with their respective bogies, filters and merges them at a common junction (HYB), and prints the departure bogie list based on distance priority.

## 🛠️ Technologies Used
- Java 17+
- Maven
- Object-Oriented Design Principles

## 📥 Input Format
Each input file will contain:

## ✅ Problem Requirements
- Filter out bogies not having a valid distance mapping from HYB.
- Merge bogies based on descending distance from HYB.
- Maintain the engine order: ENGINE from TrainA + ENGINE from TrainB.
- Print arrival and departure sequences.

## 🚀 Run Instructions

```bash
# Compile and build
mvn clean install

# Run with sample input
java -cp target/geektrust.jar com.example.geektrust.Main sample_input/input1.txt
ARRIVAL TRAIN_A ENGINE SLM NJP NGP BLR
ARRIVAL TRAIN_B ENGINE NJP GHY MAO BPL PTA
DEPARTURE TRAIN_AB ENGINE ENGINE PTA NJP GHY SLM NGP BLR BPL MAO




Let me know if you want this tailored to a specific Java version, your full name, or customized branding (e.g. logos, badges).

