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

import ca.airspeed.invoice.Customer;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Customer)
class CustomerTests {

    void testConstraints() {
       mockForConstraintsTests(Customer)
	   
	   def customer = new Customer()
	   assert !customer.validate()
	   assert "nullable" == customer.errors["fullName"]
	   assert "nullable" == customer.errors["defaultDeliveryMethod"]
	   assert "nullable" == customer.errors["customerRefListId"]
	   
	   customer.fullName = 'MegaCorp'
	   customer.defaultDeliveryMethod = 'NoSuchMethod'
	   customer.customerRefListId = '33hh774'
	   customer.company = new Company()
	   assert !customer.validate()
	   assert "inList" == customer.errors["defaultDeliveryMethod"]
	   
	   customer.defaultDeliveryMethod = 'Email'
	   assert customer.validate()
    }
}
