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
package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class InvoiceLineController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [invoiceLineInstanceList: InvoiceLine.list(params), invoiceLineInstanceTotal: InvoiceLine.count()]
    }

    def create() {
        [invoiceLineInstance: new InvoiceLine(params)]
    }

    def save() {
        def invoiceLineInstance = new InvoiceLine(params)
        if (!invoiceLineInstance.save(flush: true)) {
            render(view: "create", model: [invoiceLineInstance: invoiceLineInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), invoiceLineInstance.id])
        redirect(action: "show", id: invoiceLineInstance.id)
    }

    def show() {
        def invoiceLineInstance = InvoiceLine.get(params.id)
        if (!invoiceLineInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), params.id])
            redirect(action: "list")
            return
        }

        [invoiceLineInstance: invoiceLineInstance]
    }

    def edit() {
        def invoiceLineInstance = InvoiceLine.get(params.id)
        if (!invoiceLineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), params.id])
            redirect(action: "list")
            return
        }

        [invoiceLineInstance: invoiceLineInstance]
    }

    def update() {
        def invoiceLineInstance = InvoiceLine.get(params.id)
        if (!invoiceLineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (invoiceLineInstance.version > version) {
                invoiceLineInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'invoiceLine.label', default: 'InvoiceLine')] as Object[],
                          "Another user has updated this InvoiceLine while you were editing")
                render(view: "edit", model: [invoiceLineInstance: invoiceLineInstance])
                return
            }
        }

        invoiceLineInstance.properties = params

        if (!invoiceLineInstance.save(flush: true)) {
            render(view: "edit", model: [invoiceLineInstance: invoiceLineInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), invoiceLineInstance.id])
        redirect(action: "show", id: invoiceLineInstance.id)
    }

    def delete() {
        def invoiceLineInstance = InvoiceLine.get(params.id)
        if (!invoiceLineInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), params.id])
            redirect(action: "list")
            return
        }

        try {
            invoiceLineInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
