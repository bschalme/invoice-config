package ca.airspeed.common


import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.OpenSyncInvoice;

import com.icegreen.greenmail.util.*
// import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import grails.plugin.spock.IntegrationSpec;
import javax.mail.internet.MimeMessage
import javax.mail.Message.RecipientType

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
		def mail = [to: ["Brian Schalme <bschalme@airspeed.ca>"]]
		mail.from = "Darth Vader <dvader@empire.org>"
		mail.subject = "Your Choice of The Force"
		mail.text = "Pick one: the Good Side or the Dark Side."
		emailerService.sendEmail(mail)
		
		then:
		mail != null
		greenMail.receivedMessages.size() == 1
		MimeMessage message = greenMail.receivedMessages[0]
		message.getRecipients(RecipientType.TO)[0].toString() == "Brian Schalme <bschalme@airspeed.ca>"
		message.from[0].toString() == "Darth Vader <dvader@empire.org>"
		message.subject == "Your Choice of The Force"
		message.content == "Pick one: the Good Side or the Dark Side." + System.getProperty("line.separator")
	}
}
