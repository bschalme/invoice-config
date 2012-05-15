package ca.airspeed.invoice


class Tenant {

    static constraints = {
		name(blank: false)
    }
	
	static hasMany = [company: Company]
	String name
	
	String toString() {
		return name
	}
}
