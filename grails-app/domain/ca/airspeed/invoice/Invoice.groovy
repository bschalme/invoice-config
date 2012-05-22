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


class Invoice {

    static constraints = {
		invoiceNumber(blank:false)
		deliveryStatus(blank:false, inList:["ToBeDelivered", "Delivered", "Pending"])
		deliveryMethod(blank:false, inList:["Email", "WebService"])
		emailTemplateHtml(blank:true, nullable: true)
		emailTemplatePlain(blank:true, nullable: true)
		other(blank:true, nullable: true, maxSize: 30)
    }
	
	static belongsTo = [job:Job]
	
	static hasMany = [invoiceLine:InvoiceLine]
	String invoiceNumber
	String deliveryMethod
	String deliveryStatus
	String emailTemplateHtml
	String emailTemplatePlain
	String other
	
	String toString() {
		return invoiceNumber
	}
	
	def makeMail() {
		def mail=[:]
		return mail
	}
}
