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

import grails.gsp.PageRenderer;

import javax.mail.internet.InternetAddress;

import ca.airspeed.invoice.Customer;
import ca.airspeed.invoice.Company;
import ca.airspeed.invoice.Job;
import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.InvoiceLine;
import ca.airspeed.invoice.InvoiceRecipient;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage

class EmailerService {

	boolean transactional = false

	def mailService

	PageRenderer groovyPageRenderer

	def email(mail) {
		mailService.sendMail {
			multipart true
			mail.to.each { to it }
			from mail.from
			mail.cc.each { cc it }
			mail.bcc.each { bcc it }
			subject mail.subject
			if (mail.headers != null) {
				headers mail.headers
			}
			text mail.text
			if (mail.html != null) {
				html mail.html
			}
			mail.attachments.each { attach it }
		}
	}

	def emailInvoice(invoice) {
		Company company = invoice.job.customer.company
		def sender = new InternetAddress(company.invoiceEmail, company.invoiceFirstName + ' ' + company.invoiceLastName).toString()
		def recipients = invoice.job.invoiceRecipient.findAll {it.type == 'To'}
		def toRecipients = []
		recipients.each {
			toRecipients << new InternetAddress(it.email, it.firstName + ' ' + it.lastName).toString()
		}
		recipients = invoice.job.invoiceRecipient.findAll {it.type == 'Cc'}
		def ccRecipients = []
		recipients.each {
			ccRecipients << new InternetAddress(it.email, it.firstName + ' ' + it.lastName).toString()
		}
		recipients = invoice.job.invoiceRecipient.findAll {it.type == 'Bcc'}
		def bccRecipients = []
		recipients.each {
			bccRecipients << new InternetAddress(it.email, it.firstName + ' ' + it.lastName).toString()
		}
		def msgHeaders = ["Disposition-Notification-To": sender]
		def attachmentFiles = [
			new File('./web-app/images/grails_logo.jpg')
		]

		mailService.sendMail {
			multipart true
			to toRecipients
			from sender
			if (ccRecipients) {
				cc ccRecipients
			}
			if (bccRecipients) {
				bcc bccRecipients
			}
			subject "Invoice #${invoice.invoiceNumber} From ${company.name}"
			headers msgHeaders
			text groovyPageRenderer.render(template: "${invoice.job.emailTemplatePlain}", model: [invoice:invoice, company:invoice.job.customer.company])
			html groovyPageRenderer.render(template: "${invoice.job.emailTemplateHtml}", model: [invoice:invoice, company:invoice.job.customer.company])
			invoice.attachments.each {
				attach it.fileName, it.mimeType, it.content
			}
		}
	}
}
