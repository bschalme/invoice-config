package ca.airspeed.common

class Email {

    static constraints = {
		firstName(blank:false, maxSize:25)
		lastName(blank:true, maxSize:25)
		address(blank:false, email:true)
    }
	
	String firstName
	String lastName
	String address
	
	String toString() {
		return firstName + ' ' + lastName
	}

}
