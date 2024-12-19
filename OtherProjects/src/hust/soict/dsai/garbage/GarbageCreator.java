package hust.soict.dsai.garbage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GarbageCreator {
    private String filePath;
    public GarbageCreator(String filePath) {
        this.filePath = filePath;
    }
    public void readFileInefficiently() { // use "+" as intended
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String result = "";
            String line;
            while ((line = reader.readLine()) != null) {
                result += line; 
            }
            System.out.println("Read completed (inefficiently). Length of content: " + result.length());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}