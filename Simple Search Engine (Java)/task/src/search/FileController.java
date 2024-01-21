package search;

import java.io.*;
import java.util.Scanner;

public class FileController {

    private static final String FILE_NAME = "names.txt";

    public static void save(QueryProcessor queryProcessor) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Person person : queryProcessor.getPeopleList()) {
                writer.println(person.getFullDetails());
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public static void load(QueryProcessor queryProcessor, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(" ");
                    if (data.length >= 1) {
                        String firstName = data[0];
                        String lastName = (data.length > 1) ? data[1] : "";
                        String email = (data.length > 2) ? data[2] : "";
                        queryProcessor.getPeopleList().add(new Person(firstName, lastName, email));
                    } else {
                        System.out.println("Invalid input for a person in the file. Skipping.");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        }
    }
}
