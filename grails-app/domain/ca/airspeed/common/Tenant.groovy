package ca.airspeed.common

import ca.airspeed.accounting.Company;

class Tenant {

    static constraints = {
    }
	
	static hasMany = [company: Company]
	String name
	
	String toString() {
		return name
	}
}
