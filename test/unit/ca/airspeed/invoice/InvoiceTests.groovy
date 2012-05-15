package ca.airspeed.invoice



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Invoice)
@Mock(Job)
class InvoiceTests {

	void testConstraints() {
		mockForConstraintsTests(Invoice)

		def invoice = new Invoice(invoiceNumber: '362',
		deliveryStatus: 'ToBeDelivered',
		deliveryMethod: 'Email',
		emailTemplateHtml: '',
		emailTemplatePlain: '/tmp/blart')
		invoice.job = new Job()
		assert invoice.validate() != null
		if (invoice.errors.fieldErrorCount > 0) {
			println invoice.errors
		}
	}
}
