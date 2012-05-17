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
