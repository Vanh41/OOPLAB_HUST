package hust.soict.globalict.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        String filename = "test.exe";
        byte[] inputBytes = {0};
        long startTime, endTime;


        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));

            startTime = System.currentTimeMillis();

//            StringBuilder outputStringBuilder = new StringBuilder();
            String outputString = "";
            for (byte b : inputBytes) {
                outputString += (char) (b);
//                outputStringBuilder.append( (char) (b));
            }

            endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + "ms");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
