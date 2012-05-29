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

class Job {

    static constraints = {
			name(blank:false, maxSize:75)
			emailTemplateHtml(blank:false)
			emailTemplatePlain(blank:false)
    }
	
	static belongsTo = [customer:Customer]
	static hasMany = [invoice:Invoice, invoiceRecipient:InvoiceRecipient]
	String name
	String emailTemplateHtml
	String emailTemplatePlain

	String toString() {
		return "${customer.fullName}:${name}"
	}
}
