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

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Job)
class JobTests {

   void testConstraints() {
       mockForConstraintsTests(Job)
	   
	   def job = new Job()
	   assert !job.validate()
	   assert "nullable" == job.errors["name"]
	   assert "nullable" == job.errors["emailTemplateHtml"]
	   assert "nullable" == job.errors["emailTemplatePlain"]
	   
	   job.emailTemplateHtml = '/templates/html.gsp'
	   job.emailTemplatePlain = '/templates/plain.gsp'
	   job.customer = new Customer()
	   job.name = 'SomeBigHairyLongFrickinNameThat Is LongerThanThe 75 Character Limit Set Fot This Field'
	   assert !job.validate()
	   assert "maxSize" == job.errors["name"]
	   
	   job.name = 'Sonic ESB Integration'
	   assert job.validate()
    }
}
