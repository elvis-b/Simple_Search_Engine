package search;

public record Person(String firstName, String lastName, String email) {

    public String getFullDetails() {
        return firstName + " " + lastName + " " + email;
    }

    public String toString() {
        return getFullDetails();
    }
}
