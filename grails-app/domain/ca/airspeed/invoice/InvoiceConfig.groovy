package ca.airspeed.invoice

class InvoiceConfig {

	static constraints = {
		customerId(blank:false)
		customerNumber(blank:true, nullable:true)
		customerName(blank:false, maxSize:50)
		fromFirstName(blank:true, maxSize:50)
		fromLastName(blank:true, maxSize:50)
		fromAlias(blank:true, maxSize:20)
		fromEmail(blank:false, email:true)
		attachmentFolder(blank:false)
		invoiceFilenamePrefix(blank:false)
		timesheetFilenamePrefix(blank:false)
	}

	// static hasMany = [recipient:InvoiceRecipient]
	String customerId
	String customerNumber
	String customerName
	String fromFirstName
	String fromLastName
	String fromAlias
	String fromEmail
	String attachmentFolder
	String invoiceFilenamePrefix
	String timesheetFilenamePrefix
	
	String toString() {
		return customerName
	}
}
