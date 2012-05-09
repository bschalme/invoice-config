package ca.airspeed.common

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage

class EmailerService {
	
	boolean transactional = false
	
	MailSender mailSender

    def emailInvoice() {

    }
	
	def sendEmail(mail) {
		SimpleMailMessage message = new SimpleMailMessage()
		message.to = mail.to
		message.from = mail.from
		message.subject = mail.subject
		message.text = mail.text
		mailSender.send(message)
	}
}
