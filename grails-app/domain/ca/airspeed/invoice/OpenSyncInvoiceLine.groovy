package ca.airspeed.invoice

class OpenSyncInvoiceLine {
	
	static mapping = {
		datasource 'openSync'
		table 'invoicelinedetail'
		version false
	}

    static constraints = {
    }
	
	static belongsTo = [invoice: OpenSyncInvoice]
}
