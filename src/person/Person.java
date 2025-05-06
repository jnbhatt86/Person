package person;

/*
 * Author: Jennifer Bhatt
 * Class: Person object to store the details
 */
public class Person implements Comparable<Person> {
    protected String firstName, lastName, dob;

    public Person(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    @Override
    public int compareTo(Person o) {
        return this.lastName.compareTo(o.lastName); // or customize
    }

    @Override
    public String toString() {
        return "Person: " + firstName + " " + lastName + ", DOB: " + dob;
    }
}
