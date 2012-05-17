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

class InvoiceConfig {

	static constraints = {
		customerId(blank:false)
		customerNumber(blank:true, nullable:true)
		customerName(blank:false, maxSize:50)
		fromFirstName(blank:true, maxSize:50)
		fromLastName(blank:true, maxSize:50)
		fromAlias(blank:true, maxSize:20)
		fromEmail(blank:false, email:true)
		attachmentFolder(blank:false)
		invoiceFilenamePrefix(blank:false)
		timesheetFilenamePrefix(blank:false)
	}

	// static hasMany = [recipient:InvoiceRecipient]
	String customerId
	String customerNumber
	String customerName
	String fromFirstName
	String fromLastName
	String fromAlias
	String fromEmail
	String attachmentFolder
	String invoiceFilenamePrefix
	String timesheetFilenamePrefix
	
	String toString() {
		return customerName
	}
}
