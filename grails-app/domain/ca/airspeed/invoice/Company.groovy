/*
    Copyright 2012 Airspeed Consulting

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package ca.airspeed.invoice


class Company {

    static constraints = {
		name(blank:false, maxSize:50)
		address1(blank: false, maxSize:25)
		address2(blank: true, nullable:true, maxSize:25)
		city(blank:false, maxSize:25)
		province(blank:false, maxSize:2)
		phone(blank: false, maxSize:30)
		url(blank:true, nullable: true, url:true)
		invoiceFirstName(blank:false, maxSize:25)
		invoiceLastName(blank:true, maxSize:25)
		invoiceEmail(blank:false, email:true)
    }
	
	static hasMany = [customer:Customer]
	String name
	String address1
	String address2
	String city
	String province
	String postalCode
	String phone
	String url
	String invoiceFirstName
	String invoiceLastName
	String invoiceEmail

	String toString() {
		return name
	}
}
