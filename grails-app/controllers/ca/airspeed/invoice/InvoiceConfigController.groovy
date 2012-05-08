package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class InvoiceConfigController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static scaffold = true
}
