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

import javax.mail.internet.InternetAddress;

import org.springframework.dao.DataIntegrityViolationException

class InvoiceController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def emailerService

	def mailService

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[invoiceInstanceList: Invoice.list(params), invoiceInstanceTotal: Invoice.count()]
	}

	def create() {
		[invoiceInstance: new Invoice(params)]
	}

	def save() {
		def invoiceInstance = new Invoice(params)
		if (!invoiceInstance.save(flush: true)) {
			render(view: "create", model: [invoiceInstance: invoiceInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'invoice.label', default: 'Invoice'),
			invoiceInstance.id
		])
		redirect(action: "show", id: invoiceInstance.id)
	}

	def show() {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[invoiceInstance: invoiceInstance]
	}

	def edit() {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[invoiceInstance: invoiceInstance]
	}

	def update() {
		myUpdate('default.updated.message')
	}

	def myUpdate(successCode) {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (invoiceInstance.version > version) {
				invoiceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'invoice.label', default: 'Invoice')]
						as Object[],
						"Another user has updated this Invoice while you were editing")
				render(view: "edit", model: [invoiceInstance: invoiceInstance])
				return
			}
		}

		invoiceInstance.properties = params

		if (!invoiceInstance.save(flush: true)) {
			render(view: "edit", model: [invoiceInstance: invoiceInstance])
			return
		}

		flash.message = message(code: successCode, args: [
			message(code: 'invoice.label', default: 'Invoice'),
			invoiceInstance.id
		])
		redirect(action: "show", id: invoiceInstance.id)
	}

	def delete() {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			invoiceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}

	def email() {
		Invoice invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}
		
		emailerService.emailInvoice(invoiceInstance)
		params.deliveryStatus = 'Delivered'
		myUpdate('emailed.message')
	}
}
