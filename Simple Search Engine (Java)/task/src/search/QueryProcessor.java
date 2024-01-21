package search;

import java.util.*;

public class QueryProcessor {
    private final List<Person> peopleList;
    private final Map<String, List<Integer>> invertedIndex;

    public QueryProcessor() {
        this.peopleList = new ArrayList<>();
        this.invertedIndex = new HashMap<>();
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    private Map<String, List<Integer>> getInvertedIndex() {
        return invertedIndex;
    }

    /**
     * Builds the inverted index for efficient search operations.
     */
    public void buildInvertedIndex() {
        for (int i = 0; i < peopleList.size(); i++) {
            Person person = peopleList.get(i);
            String[] words = (person.getFullDetails()).split("\\s+");

            for (String word : words) {
                if (invertedIndex.get(word) == null) {
                    List<Integer> lineNumber = new ArrayList<>();
                    lineNumber.add(i);
                    invertedIndex.put(word, lineNumber);
                } else {
                    invertedIndex.get(word).add(i);
                }
            }
        }
    }

    /**
     * Selects a matching strategy based on user input.
     *
     * @return The selected strategy.
     */

    public Strategy selectStrategy() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();
        return switch (selection) {
            case "ALL" -> Strategy.ALL;
            case "ANY" -> Strategy.ANY;
            case "NONE" -> Strategy.NONE;
            default -> {
                System.out.println("Invalid search strategy. Please use ALL, ANY, or NONE.");
                yield Strategy.ALL;
            }
        };
    }

    /**
     * Finds and displays search results based on the selected strategy.
     *
     * @param strategy The matching strategy to apply.
     */

    public void findResults(Strategy strategy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a name or email to search all suitable people:");
        String query = scanner.nextLine().toLowerCase();
        switch (strategy) {
            case ALL -> strategyAll(query);
            case ANY -> strategyAny(query);
            case NONE -> strategyNone(query);
            default -> throw new IllegalArgumentException();
        }
    }

    private void strategyNone(String query) {
        Set<Integer> searchResults = new HashSet<>();
        String[] searchWords = query.split("\\s+");
        for (String word: searchWords) {
            if (invertedIndex.containsKey(word)) {
                searchResults.addAll(invertedIndex.get(word));
            }
        }

        Set<Integer> allResults = new HashSet<>();
        for (int i = 0; i < peopleList.size(); i++) {
            allResults.add(i);
        }

        allResults.removeAll(searchResults);
        if (allResults.isEmpty()) {
            System.out.println("No matching results");
        } else {
            System.out.println();
            System.out.printf("%d persons found:%n", allResults.size());
            for (Integer currentIndex : allResults) {
                System.out.println(peopleList.get(currentIndex));
            }
        }
    }

    private void strategyAll(String query) {
        Set<Integer> searchIndexResult = new HashSet<>();
        for (String queryWord : query.split("\\s+")) {
            if (getInvertedIndex().containsKey(queryWord)) {
                List<Integer> indexes = invertedIndex.get(queryWord);
                searchIndexResult.addAll(indexes);
            } else {
                System.out.println("No matching persons found.");
                return;
            }
        }

        List<Person> result = new ArrayList<>();
        for (Integer index : searchIndexResult) {
            Person currentPerson = getPeopleList().get(index);
            List<String> personData = Arrays.asList(currentPerson.getFullDetails().split("\\s+"));

            boolean containsAll = new HashSet<>(personData).containsAll(Arrays.asList(query.split("\\s+")));
            if (containsAll) {
                result.add(currentPerson);
            }
        }

        if (result.isEmpty()) {
            System.out.println("NO_ONE_FOUND");
        } else {
            System.out.printf("%d persons found:%n", result.size());
            result.forEach(person -> System.out.println(person.getFullDetails()));
        }
    }



    private void strategyAny(String query) {
        Set<Integer> searchResults = new HashSet<>();
        String[] searchWords = query.split("\\s+");
        for (String word: searchWords) {
            if (invertedIndex.containsKey(word)) {
                searchResults.addAll(invertedIndex.get(word));
            }
        }

        if (!searchResults.isEmpty()) {
            System.out.println();
            System.out.printf("%d persons found: %n", searchResults.size());
            for (Integer result : searchResults) {
                System.out.println(peopleList.get(result));
            }
        } else {
            System.out.println("No matching people found.");
            }
        }

    public void printAllPeople() {
        System.out.println("=== List of people ===");
        for (Person person : peopleList) {
            System.out.println(person.getFullDetails());
        }
    }
}
