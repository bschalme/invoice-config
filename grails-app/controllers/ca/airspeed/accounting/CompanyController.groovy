package ca.airspeed.accounting

import org.springframework.dao.DataIntegrityViolationException

class CompanyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def scaffold = true
}
