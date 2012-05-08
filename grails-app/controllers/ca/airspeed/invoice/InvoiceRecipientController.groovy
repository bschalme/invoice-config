package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class InvoiceRecipientController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def scaffold = true
}
