package ca.airspeed.invoice


class InvoiceRecipient {

	static constraints = {
		type(inList:["To", "Cc", "Bcc"])
		firstName()
		lastName()
		email(email:true)
	}

	static belongsTo = [job:Job]

	String type
	String firstName
	String lastName
	String email

	String toString() {
		return type + ': ' + firstName + ' ' + lastName
	}
}
