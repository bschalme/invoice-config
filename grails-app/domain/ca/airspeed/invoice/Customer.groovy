package ca.airspeed.invoice


class Customer {

    static constraints = {
		fullName()
		defaultDeliveryMethod(blank:false, inList:["Email", "WebService"])
    }
	
	static belongsTo = [company:Company]
	static hasMany = [job:Job, invoiceRecipient:InvoiceRecipient]
	String customerRefListId
	String fullName
	String defaultDeliveryMethod
	
	String toString() {
		return fullName
	}
}
