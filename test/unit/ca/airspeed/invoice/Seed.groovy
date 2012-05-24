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

/**
 * @author Brian Schalme
 *
 */
class Seed {

	static void seedDb() {
		def airspeed = new Company(name:'TEST Airspeed Consulting', address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB', postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
		def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
		def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain', emailTemplateHtml:'/templates/MegaCorp/MegaCorpHtml').save(failOnError: true)
		def toRecipient = new InvoiceRecipient(job: sonicEsb, type: 'To', firstName: 'Joe', lastName: 'Director', email: 'jdirector@megacorp.com').save(failOnError: true)
		def ccRecipient = new InvoiceRecipient(job: sonicEsb, type: 'Cc', firstName: 'Jane', lastName: 'Vendor', email: 'jvendor@megacorp.com').save(failOnError: true)
		
	}
}
