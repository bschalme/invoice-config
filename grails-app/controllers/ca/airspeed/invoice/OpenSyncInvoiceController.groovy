package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class OpenSyncInvoiceController {

    static allowedMethods = []

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [openSyncInvoiceInstanceList: OpenSyncInvoice.list(params), openSyncInvoiceInstanceTotal: OpenSyncInvoice.count()]
    }

    def show() {
        def openSyncInvoiceInstance = OpenSyncInvoice.get(params.id)
        if (!openSyncInvoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'openSyncInvoice.label', default: 'OpenSyncInvoice'), params.id])
            redirect(action: "list")
            return
        }

        [openSyncInvoiceInstance: openSyncInvoiceInstance]
    }

}
