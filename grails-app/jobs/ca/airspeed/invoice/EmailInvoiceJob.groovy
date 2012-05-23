package ca.airspeed.invoice



class EmailInvoiceJob {
	
	def concurrent = false
	
	def emailerService
	
    /*static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }*/
	
	def getTriggers(){
		return config.grails.plugin.invoice.emailInvoiceTrigger
	}

    def execute() {
		def toDo = Invoice.findByDeliveryMethodAndDeliveryStatus('Email', 'ToBeDelivered')
		toDo.each {
			emailerService.emailInvoice(it)
			it.deliveryStatus = 'Delivered'
			it.save(flush: true)
			log.info 'Invoice ' + it + ' for ' + it.job.customer + ' emailed to ' + it.job.invoiceRecipient
		}
    }
}
