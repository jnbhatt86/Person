package person;

import java.io.Serializable;

/*
 * Author: Jennifer Bhatt
 * Class: OCCCPerson's details where First Name, Last Name and 
 * Government ID comes from super class 
 */
public class OCCCPerson extends RegisteredPerson implements Serializable{
	public String studentID;
	public OCCCPerson(Person person, String governmentID, String studentID) {
        super(person, governmentID);
        this.studentID = studentID;
    }
	public OCCCPerson(RegisteredPerson registeredPerson, String studentID) {
	    super(registeredPerson.firstName, registeredPerson.lastName,registeredPerson.dob, registeredPerson.governmentID);
	    this.studentID = studentID;
	}
    @Override
    public String toString() {
        return "OCCCPerson: " + firstName + " " + lastName + ", GovID: " + governmentID + ", StudentID: " + studentID;
    }
	
	
}
