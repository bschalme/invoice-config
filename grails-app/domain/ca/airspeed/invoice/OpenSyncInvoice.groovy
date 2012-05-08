package ca.airspeed.invoice

class OpenSyncInvoice {

	static mapping = {
		datasource 'openSync'
		table 'invoice'
		version false
		id column: 'TxnID'
		invoiceNumber column: 'RefNumber'
		customerJob column: 'CustomerRef_FullName'
		invoiceDate column: 'TxnDate'
		invoiceTotal column: 'Subtotal'
	}

	static constraints = {
	}
	
	static hasMany = [line:OpenSyncInvoiceLine]

	String id
	String invoiceNumber
	String customerJob
	Date invoiceDate
	Float invoiceTotal

	String toString() {
		return invoiceNumber
	}
}
