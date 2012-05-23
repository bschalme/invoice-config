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
package ca.airspeed.common


import ca.airspeed.invoice.Customer;
import ca.airspeed.invoice.Company;
import ca.airspeed.invoice.Job;
import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.InvoiceLine;
import ca.airspeed.invoice.InvoiceRecipient;

import com.icegreen.greenmail.util.*
// import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import grails.plugin.spock.IntegrationSpec;
import javax.mail.internet.MimeMessage
import javax.mail.Message.RecipientType
import javax.mail.Multipart;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class EmailerServiceSpec extends IntegrationSpec {
	def greenMail
	def emailerService

	def setup() {
		greenMail.deleteAllMessages()
	}

	def "greenmail was initialized"() {
		expect:
		greenMail != null
	}

	def "emailerService was initialized"() {
		expect:
		emailerService != null
	}

	def "send a text-only email, no Cc, no Bcc"() {
		when:
		def mail = [to: [
				"Brian Schalme <bschalme@airspeed.ca>"
			]]
		mail.from = "Darth Vader <dvader@empire.org>"
		mail.subject = "Your Choice of The Force"
		mail.text = "Pick one: the Good Side or the Dark Side."
		emailerService.email(mail)

		then:
		greenMail.receivedMessages.size() == 1
		MimeMessage message = greenMail.receivedMessages[0]
		message.getRecipients(RecipientType.TO)[0].toString() == mail.to[0]
		message.from[0].toString() == mail.from
		message.getRecipients(RecipientType.CC) == null
		message.subject == mail.subject
		message.content instanceof Multipart
		message.content.count == 1
		message.content.getBodyPart(0).content.count == 1
		message.content.getBodyPart(0).content.getBodyPart(0).content == mail.text
	}

	def "send a multipart email with Cc, Bcc and attachments"() {
		when:
		def mail = [to: [
				"Brian Schalme <bschalme@airspeed.ca>"
			]]
		mail.from = "Darth Vader <dvader@empire.org>"
		mail.cc = [
			'Darth Maul <dmaul@empire.org>'
		]
		mail.bcc = [
			'Leia Organa <Leia.Organa@rebellion.net>'
		]
		mail.subject = "Your Choice of The Force"
		mail.headers = ['Disposition-Notification-To': 'Darth Vader <dvader@empire.org>']
		mail.text = "Pick one: the Good Side or the Dark Side."
		mail.html = "<html><head></head><body><p>Hello World!</p></body></html>"
		mail.attachments = [
			new File('./web-app/images/grails_logo.jpg')
		]
		emailerService.email(mail)

		then:
		mail != null
		greenMail.receivedMessages.size() == 3
		MimeMessage message = greenMail.receivedMessages[0]
		message.getRecipients(RecipientType.TO)[0].toString() == mail.to[0]
		message.from[0].toString() == mail.from
		message.getRecipients(RecipientType.CC) != null
		message.getRecipients(RecipientType.CC)[0].toString() == mail.cc[0]
		message.subject == mail.subject
		message.getHeader("Disposition-Notification-To") != null
		message.getHeader("Disposition-Notification-To")[0] == 'Darth Vader <dvader@empire.org>'
		message.content instanceof Multipart
		message.content.count == 2
		message.content.getBodyPart(0).content.getBodyPart(0).content.count == 2
		message.content.getBodyPart(0).content.getBodyPart(0).content.getBodyPart(0).content == mail.text
		message.content.getBodyPart(0).content.getBodyPart(0).content.getBodyPart(1).content == mail.html
		def attachment = message.content.getBodyPart(1)
		attachment.isMimeType('image/jpeg')
		attachment.contentType == 'image/jpeg; name=grails_logo.jpg'
	}

	def "email an Invoice"() {
		when:
		def airspeed = new Company(name:'TEST Airspeed Consulting', address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB', postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true, flush: true)
		def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true, flush: true)
		def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain', emailTemplateHtml:'/templates/MegaCorp/MegaCorpHtml').save(failOnError: true, flush: true)
		def inv362 = new Invoice(job:sonicEsb, invoiceNumber:'362', deliveryMethod:'Email', deliveryStatus:'ToBeDelivered', other:'Mar 1 - 31, 2012').save(failOnError: true, flush:true)
		def line1 = new InvoiceLine(invoice:inv362, quantity:96.00F, itemName:'A&P:$100/hr', description:'Analysis & Programming Services', rate:100.00F, amount:9600.00F)
		line1.invoice = inv362
		line1.save(failOnError:true, flush: true)
		def line2 = new InvoiceLine(invoice:inv362, quantity:1.00F, itemName:'Charm', description:'Charm and Wit', rate:100.00F, amount:100.00F)
		line2.invoice = inv362
		line2.save(failOnError:true, flush: true)
		inv362.invoiceLine = [line1, line2]
		inv362.save(failOnError: true, flush:true)
		def to = new InvoiceRecipient(job:sonicEsb, type:'To', firstName:'Brian', lastName:'Schalme', email:'bschalme@airspeed.ca').save(failOnError: true, flush: true)
		sonicEsb.invoiceRecipient = [to]
		def invoice = Invoice.findByInvoiceNumber('362')
		emailerService.emailInvoice(invoice)
		
		then:
		greenMail.receivedMessages.size() == 1
		MimeMessage message = greenMail.receivedMessages[0]
		message.getRecipients(RecipientType.TO)[0].toString() == 'Brian Schalme <bschalme@airspeed.ca>'
		message.getRecipients(RecipientType.CC) == null
		message.subject == 'Invoice #362 From TEST Airspeed Consulting'
		message.getHeader("Disposition-Notification-To") != null
		message.getHeader("Disposition-Notification-To")[0] == 'Brian Schalme <bschalme@airspeed.ca>'
		message.content instanceof Multipart
	}
}
