package person;

import java.io.Serializable;

/*
 * Author: Jennifer Bhatt
 * Class: Registered Person's object which stores their details
 */
public class RegisteredPerson extends Person implements Serializable{

	public String governmentID;
	
	public RegisteredPerson(String firstName, String lastName, String dob, String governmentID) {
	    super(firstName, lastName, dob);
	    this.governmentID = governmentID;
	}
	public RegisteredPerson(Person person, String governmentID) {
        super(person.firstName, person.lastName, person.dob); // Copy Person details
        this.governmentID = governmentID;
    }
	
	

    @Override
    public String toString() {
        return "RegisteredPerson: " + firstName + " " + lastName + ", GovID: " + governmentID;
    }
	
	
}
