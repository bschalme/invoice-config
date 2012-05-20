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
		
		if (!Tenant.count()) {
			def tenant = new Tenant(name:'4020774 Manitoba Ltd.').save(failOnError: true)
			def airspeed = new Company(tenant:tenant, name:'TEST Airspeed Consulting', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
			def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
			def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain', emailTemplateHtml:'E:/Airspeed/MegaCorp/Templates/MegaCorpHtml.gsp').save(failOnError: true)
			def inv362 = new Invoice(job:sonicEsb, invoiceNumber:'362', deliveryMethod:'Email', deliveryStatus:'ToBeDelivered').save(failOnError: true)
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
