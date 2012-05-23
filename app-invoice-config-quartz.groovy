grails.plugin.quartz2.autoStartup = true

grails.plugin.invoice.emailInvoiceTrigger = {
	simple repeatInterval: 5000l // execute job once in 5 seconds
}