import ca.airspeed.invoice.Company
import ca.airspeed.invoice.Customer
import ca.airspeed.invoice.Invoice
import ca.airspeed.invoice.InvoiceRecipient
import ca.airspeed.invoice.Job
import ca.airspeed.invoice.Tenant;

class BootStrap {

	def init = { servletContext ->
		environments {
			development {
				if (!Tenant.count()) {
					def tenant = new Tenant(name:'4020774 Manitoba Ltd.').save(failOnError: true)
					def airspeed = new Company(tenant:tenant, name:'TEST Airspeed Consulting', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
					def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
					def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'E:/Airspeed/MegaCorp/Templates/MegaCorpPlain.gsp', emailTemplateHtml:'E:/Airspeed/MegaCorp/Templates/MegaCorpHtml.gsp').save(failOnError: true)
					def inv362 = new Invoice(job:sonicEsb, invoiceNumber:'362', deliveryMethod:'Email', deliveryStatus:'ToBeDelivered').save(failOnError: true)
					def to = new InvoiceRecipient(job:sonicEsb, type:'To', firstName:'Brian', lastName:'Schalme', email:'bschalme@airspeed.ca').save(failOnError: true)
				}
			}
		}
	}

	def destroy = {
	}
}
