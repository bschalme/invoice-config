package ca.airspeed.common

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage

class EmailerService {

	boolean transactional = false

	def mailService

	def emailInvoice(mail) {
		mailService.sendMail {
			multipart true
			mail.to.each { to it }
			from mail.from
			mail.cc.each {
				cc it
			}
			mail.bcc.each {
				bcc it
			}
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
}
