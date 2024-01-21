package search;

import java.util.Scanner;

public class MenuController {
    private final QueryProcessor queryProcessor;

    public MenuController(String fileName) {
        this.queryProcessor = new QueryProcessor();
        FileController.load(queryProcessor, fileName); // Load data from file
        queryProcessor.buildInvertedIndex(); // Build inverted index
    }

    public void menuOptions() {
        System.out.print("""
                === Menu ===
                1. Find a person.
                2. Print all persons.
                0. Exit.
                """);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menuOptions();
            int action = scanner.nextInt();
            if (action < 0 || action > 2) {
                System.out.println("Incorrect option! Try again");
                System.out.println();
            } else {
                switch (action) {
                    case 1:
                        System.out.println();
                        Strategy strategy = queryProcessor.selectStrategy();
                        queryProcessor.findResults(strategy);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println();
                        queryProcessor.printAllPeople();
                        System.out.println();
                        break;
                    case 0:
                        FileController.save(queryProcessor);
                        System.out.println();
                        System.out.println("Bye!");
                        System.exit(0);
                }
            }
        }
    }
}