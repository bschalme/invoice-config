package ca.airspeed.invoice


class Tenant {

    static constraints = {
    }
	
	static hasMany = [company: Company]
	String name
	
	String toString() {
		return name
	}
}
