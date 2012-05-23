package ca.airspeed.invoice

import static org.junit.Assert.*

import grails.plugin.spock.IntegrationSpec;
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
// @TestMixin(GrailsUnitTestMixin)
class InvoiceControllerSpec extends IntegrationSpec {
	def greenMail
	def invoiceController
	def mailService

	def setup() {
		greenMail.deleteAllMessages()
		invoiceController = new InvoiceController()
		invoiceController.mailService = mailService

		if (!Company.count()) {
			def airspeed = new Company(name:'TEST Airspeed Consulting', address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB', postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
			def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
			def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain', emailTemplateHtml:'E:/Airspeed/MegaCorp/Templates/MegaCorpHtml.gsp').save(failOnError: true)
			def inv362 = new Invoice(job:sonicEsb, invoiceNumber:'362', deliveryMethod:'Email', deliveryStatus:'ToBeDelivered').save(failOnError: true)
			def line1 = new InvoiceLine(invoice:inv362, quantity:96.00F, itemName:'A&P:$100/hr', description:'Analysis & Programming Services', rate:100.00F, amount:9600.00F)
			line1.invoice = inv362
			line1.save(failOnError:true, flush: true)
			def line2 = new InvoiceLine(invoice:inv362, quantity:1.00F, itemName:'Charm', description:'Charm and Wit', rate:100.00F, amount:100.00F)
			line2.invoice = inv362
			line2.save(failOnError:true, flush: true)
			inv362.invoiceLine = [line1, line2]
			inv362.save(failOnError: true, flush:true)
			def to = new InvoiceRecipient(job:sonicEsb, type:'To', firstName:'Brian', lastName:'Schalme', email:'bschalme@airspeed.ca').save(failOnError: true)
			def cc = new InvoiceRecipient(job: sonicEsb, type: 'Cc', firstName: 'Jane', lastName: 'Vendor', email: 'jvendor@megacorp.com').save(failOnError: true)
			sonicEsb.invoiceRecipient = [to, cc]
		}

	}

	def "Greenmail was initialized"() {
		expect:
		greenMail != null
	}

	def "InvoiceController was initialized"() {
		expect:
		invoiceController != null
	}

	def "Emails an invoice"() {
		when:
		invoiceController.params.id = Invoice.findByInvoiceNumber('362').id
		invoiceController.email()

		then:
		greenMail.receivedMessages.size() == 2
	}
}
