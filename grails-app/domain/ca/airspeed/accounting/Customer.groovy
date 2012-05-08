package ca.airspeed.accounting

import ca.airspeed.common.Email
import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.InvoiceRecipient

class Customer {

    static constraints = {
		fullName()
		defaultDeliveryMethod(blank:false, inList:["Email", "WebService"])
    }
	
	static belongsTo = [company:Company]
	static hasMany = [invoice:Invoice, invoiceRecipient:InvoiceRecipient]
	String customerRefListId
	String fullName
	String defaultDeliveryMethod
	
	String toString() {
		return fullName
	}
}
