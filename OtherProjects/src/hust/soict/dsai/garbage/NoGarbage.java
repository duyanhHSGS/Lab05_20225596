package hust.soict.dsai.garbage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NoGarbage {
	private String filePath;
    public NoGarbage(String filePath) {
        this.filePath = filePath;
    }
    public void readFileEfficiently() { //intended not o use +
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            System.out.println("Read completed (efficiently). Length of content: " + result.length());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}