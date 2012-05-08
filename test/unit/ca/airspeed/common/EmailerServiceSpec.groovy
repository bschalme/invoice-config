package ca.airspeed.common


import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.OpenSyncInvoice;

import com.icegreen.greenmail.util.*
// import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import grails.plugin.spock.IntegrationSpec;

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
	
	def "sends a simple email"() {
		when:
		def mail = [to: "Brian Schalme <bschalme@airspeed.ca>"]
		mail.from = "Darth Vader <dvader@empire.org>"
		mail.subject = "Your Choice of The Force"
		mail.body = "Your choice: the Good Side or the Dark Side."
		emailerService.sendEmail(mail)
		
		then:
		mail != null
		greenMail.receivedMessages.size() == 1
	}
}
