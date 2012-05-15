package ca.airspeed.common

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage

class EmailerService {
	
	boolean transactional = false
	
	MailSender mailSender
	
	def mailService

    def emailInvoice(mail) {
		mailService.sendMail {
			to  mail.to
			from mail.from
			subject mail.subject
			body mail.text
		}
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
