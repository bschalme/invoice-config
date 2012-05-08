package ca.airspeed.invoice

import ca.airspeed.accounting.Customer;

class Invoice {

    static constraints = {
		invoiceNumber(blank:false)
		deliveryStatus(blank:false, inList:["ToBeDelivered", "Delivered", "Pending"])
		deliveryMethod(blank:false, inList:["Email", "WebService"])
		emailTemplateHtml()
		emailTemplatePlain(blank:false)
    }
	
	static belongsTo = [customer: Customer]
	
	String invoiceNumber
	String deliveryMethod
	String deliveryStatus
	String emailTemplateHtml
	String emailTemplatePlain
	
	String toString() {
		return invoiceNumber
	}
}
