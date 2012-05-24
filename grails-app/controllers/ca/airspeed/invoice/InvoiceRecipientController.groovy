package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class InvoiceRecipientController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [invoiceRecipientInstanceList: InvoiceRecipient.list(params), invoiceRecipientInstanceTotal: InvoiceRecipient.count()]
    }

    def create() {
        [invoiceRecipientInstance: new InvoiceRecipient(params)]
    }

    def save() {
        def invoiceRecipientInstance = new InvoiceRecipient(params)
        if (!invoiceRecipientInstance.save(flush: true)) {
            render(view: "create", model: [invoiceRecipientInstance: invoiceRecipientInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), invoiceRecipientInstance.id])
        redirect(action: "show", id: invoiceRecipientInstance.id)
    }

    def show() {
        def invoiceRecipientInstance = InvoiceRecipient.get(params.id)
        if (!invoiceRecipientInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), params.id])
            redirect(action: "list")
            return
        }

        [invoiceRecipientInstance: invoiceRecipientInstance]
    }

    def edit() {
        def invoiceRecipientInstance = InvoiceRecipient.get(params.id)
        if (!invoiceRecipientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), params.id])
            redirect(action: "list")
            return
        }

        [invoiceRecipientInstance: invoiceRecipientInstance]
    }

    def update() {
        def invoiceRecipientInstance = InvoiceRecipient.get(params.id)
        if (!invoiceRecipientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (invoiceRecipientInstance.version > version) {
                invoiceRecipientInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient')] as Object[],
                          "Another user has updated this InvoiceRecipient while you were editing")
                render(view: "edit", model: [invoiceRecipientInstance: invoiceRecipientInstance])
                return
            }
        }

        invoiceRecipientInstance.properties = params

        if (!invoiceRecipientInstance.save(flush: true)) {
            render(view: "edit", model: [invoiceRecipientInstance: invoiceRecipientInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), invoiceRecipientInstance.id])
        redirect(action: "show", id: invoiceRecipientInstance.id)
    }

    def delete() {
        def invoiceRecipientInstance = InvoiceRecipient.get(params.id)
        if (!invoiceRecipientInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), params.id])
            redirect(action: "list")
            return
        }

        try {
            invoiceRecipientInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
