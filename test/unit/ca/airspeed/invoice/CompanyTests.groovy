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



import grails.test.mixin.*
import org.junit.*

import ca.airspeed.invoice.Company;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Company)
class CompanyTests {

    void testConstraints() {
       mockForConstraintsTests(Company)
	   
	   def company = new Company()
	   assert !company.validate()
	   assert "nullable" == company.errors["name"]
	   assert "nullable" == company.errors["address1"]
	   assert "nullable" == company.errors["city"]
	   assert "nullable" == company.errors["province"]
	   assert "nullable" == company.errors["phone"]
	   assert "nullable" == company.errors["invoiceFirstName"]
	   assert "nullable" == company.errors["invoiceEmail"]
	   
	   company = new Company(name:'TEST Airspeed Consulting', 
		   address1:'25 Somewhere Ave.', 
		   city:'Winnipeg', province:'BlahBlah', 
		   postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', 
		   url:'http://www.airspeed.ca', invoiceFirstName:'Brian', 
		   invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca')
	   assert !company.validate()
	   assert "maxSize" == company.errors["province"]
	   company.province = 'MB'
	   
	   company.url = 'www.domain.com'
	   assert !company.validate()
	   assert "url" == company.errors["url"]
	   
	   company.url = 'http://www.domain.com'
	   assert company.validate()
    }
}
