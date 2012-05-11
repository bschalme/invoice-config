package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class CompanyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def scaffold = true
}
