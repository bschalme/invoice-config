package ca.airspeed.invoice

import ca.airspeed.accounting.Customer

class InvoiceRecipient {

	static constraints = {
		type(inList:["To", "Cc", "Bcc"])
		firstName()
		lastName()
		email(email:true)
	}

	static belongsTo = [customer:Customer]

	String type
	String firstName
	String lastName
	String email

	String toString() {
		return type + ': ' + firstName + ' ' + lastName
	}
}
