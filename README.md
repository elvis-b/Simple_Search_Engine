
# Simple Search Engine

A simple Java program for searching and managing a list of people using different matching strategies.

The application was developed as part of the Java Backend Developer track on Hyperskill: https://hyperskill.org/projects/66?track=12


## Usage

```bash
java Main --data <file_name>

```


## Features

- Find a person using various matching strategies.
Select this option to find a person using various matching strategies. You will be prompted to choose a matching strategy (ALL, ANY, or NONE), and then enter a name or email to search.

- Print all persons in the list.
Select this option to print details of all the persons in the list.



- Save and load data from a file.




## File Structure

- Main.java: Main entry point of the program.

- FileController.java: Handles saving and loading data from a file.

- MenuController.java: Manages the user interface and menu options.

- Person.java: Represents a person's details.

- QueryProcessor.java: Processes queries and performs search operations using the following data structures:

    List<Person>: Stores information about people in the program.

    Map<String, List<Integer>>: Represents the inverted index for efficient search.

- Strategy.java: Enumerates the matching strategies.

## Data Structures

- List<Person>

The List data structure is used to store instances of the Person class, representing individual records in the program. It allows for easy traversal and manipulation of the collection of people.

- Map<String, List<Integer>

The Map data structure is employed to build an inverted index for efficient search operations. The keys are words extracted from the details of each person, and the values are lists of integers representing the indices of people containing those words. This structure enables quick retrieval of matching results during searches.
