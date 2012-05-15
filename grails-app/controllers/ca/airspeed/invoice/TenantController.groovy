package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class TenantController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tenantInstanceList: Tenant.list(params), tenantInstanceTotal: Tenant.count()]
    }

    def create() {
        [tenantInstance: new Tenant(params)]
    }

    def save() {
        def tenantInstance = new Tenant(params)
        if (!tenantInstance.save(flush: true)) {
            render(view: "create", model: [tenantInstance: tenantInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tenant.label', default: 'Tenant'), tenantInstance.id])
        redirect(action: "show", id: tenantInstance.id)
    }

    def show() {
        def tenantInstance = Tenant.get(params.id)
        if (!tenantInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect(action: "list")
            return
        }

        [tenantInstance: tenantInstance]
    }

    def edit() {
        def tenantInstance = Tenant.get(params.id)
        if (!tenantInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect(action: "list")
            return
        }

        [tenantInstance: tenantInstance]
    }

    def update() {
        def tenantInstance = Tenant.get(params.id)
        if (!tenantInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tenantInstance.version > version) {
                tenantInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tenant.label', default: 'Tenant')] as Object[],
                          "Another user has updated this Tenant while you were editing")
                render(view: "edit", model: [tenantInstance: tenantInstance])
                return
            }
        }

        tenantInstance.properties = params

        if (!tenantInstance.save(flush: true)) {
            render(view: "edit", model: [tenantInstance: tenantInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tenant.label', default: 'Tenant'), tenantInstance.id])
        redirect(action: "show", id: tenantInstance.id)
    }

    def delete() {
        def tenantInstance = Tenant.get(params.id)
        if (!tenantInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tenantInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
