package com.example.geektrust; // Test package can be same or different

import com.example.geektrust.service.TrainMergerService;
import org.junit.jupiter.api.AfterEach; // For cleanup
import org.junit.jupiter.api.BeforeEach; // For setup
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainMergerServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testSampleInput1() {
        String trainAInput = "TRAIN_A ENGINE NDL NDL KRN GHY SLM NJP NGP BLR";
        String trainBInput = "TRAIN_B ENGINE NJP GHY AGA PNE MAO BPL PTA";

        TrainMergerService service = new TrainMergerService();
        service.processTrains(trainAInput, trainBInput);

        String expectedOutput = "ARRIVAL TRAIN_A ENGINE NDL NDL GHY NJP NGP\n" +
                "ARRIVAL TRAIN_B ENGINE NJP GHY AGA BPL PTA\n" +
                "DEPARTURE TRAIN_AB ENGINE ENGINE GHY GHY NJP NJP PTA NDL NDL AGA BPL NGP\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testSampleInput2() {
        String trainAInput = "TRAIN_A ENGINE SLM BLR KRN HYB SLM NGP ITJ";
        String trainBInput = "TRAIN_B ENGINE SRR MAO NJP PNE PTA";

        TrainMergerService service = new TrainMergerService();
        service.processTrains(trainAInput, trainBInput);

        String expectedOutput = "ARRIVAL TRAIN_A ENGINE HYB NGP ITJ\n" +
                "ARRIVAL TRAIN_B ENGINE NJP PTA\n" +
                "DEPARTURE TRAIN_AB ENGINE ENGINE NJP PTA ITJ NGP\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testJourneyEndedCase() {
        // Train A has only bogies that detach before Hyderabad
        String trainAInput = "TRAIN_A ENGINE SLM BLR KRN";
        // Train B also has only bogies that detach before Hyderabad
        String trainBInput = "TRAIN_B ENGINE SRR MAO PNE";

        TrainMergerService service = new TrainMergerService();
        service.processTrains(trainAInput, trainBInput);

        String expectedOutput = "ARRIVAL TRAIN_A ENGINE\n" + // Engine only, no bogies continue
                "ARRIVAL TRAIN_B ENGINE\n" + // Engine only, no bogies continue
                "DEPARTURE TRAIN_AB JOURNEY_ENDED\n"; //

        assertEquals(expectedOutput, outContent.toString());
    }
    // However, for this problem, testing the full flow might be sufficient if components are simple.
}