package ca.airspeed.invoice


class Invoice {

    static constraints = {
		invoiceNumber(blank:false)
		deliveryStatus(blank:false, inList:["ToBeDelivered", "Delivered", "Pending"])
		deliveryMethod(blank:false, inList:["Email", "WebService"])
		emailTemplateHtml()
		emailTemplatePlain(blank:false)
    }
	
	static belongsTo = [job:Job]
	
	static hasMany = [invoiceLine:InvoiceLine]
	String invoiceNumber
	String deliveryMethod
	String deliveryStatus
	String emailTemplateHtml
	String emailTemplatePlain
	
	String toString() {
		return invoiceNumber
	}
	
	def makeMail() {
		def mail=[:]
		return mail
	}
}
