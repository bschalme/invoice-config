package ca.airspeed.invoice


class Company {

    static constraints = {
		name(blank:false, maxSize:50)
		invoiceFirstName(blank:false, maxSize:25)
		invoiceLastName(blank:true, maxSize:25)
		invoiceEmail(blank:false, email:true)
    }
	
	static belongsTo = [tenant: Tenant]
	
	static hasMany = [customer:Customer]
	String name
	String invoiceFirstName
	String invoiceLastName
	String invoiceEmail

	String toString() {
		return name
	}
}
