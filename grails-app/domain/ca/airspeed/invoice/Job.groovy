package ca.airspeed.invoice

class Job {

    static constraints = {
			name(blank:false, maxSize:75)
    }
	
	static belongsTo = [customer:Customer]
	static hasMany = [invoice:Invoice, invoiceRecipient:InvoiceRecipient]
	String name
	
	String toString() {
		return name
	}
}
