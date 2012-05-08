package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class InvoiceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def scaffold = true
}
