import ca.airspeed.invoice.Company
import ca.airspeed.invoice.Customer
import ca.airspeed.invoice.Invoice
import ca.airspeed.invoice.InvoiceLine;
import ca.airspeed.invoice.InvoiceRecipient
import ca.airspeed.invoice.Job
import ca.airspeed.invoice.Tenant;

class BootStrap {

	def init = { servletContext ->
		environments {
			development {
				if (!Tenant.count()) {
					def tenant = new Tenant(name:'4020774 Manitoba Ltd.').save(failOnError: true)
					def airspeed = new Company(tenant:tenant, name:'TEST Airspeed Consulting', address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB', postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
					def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
					def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain', emailTemplateHtml:'/templates/MegaCorp/MegaCorpHtml').save(failOnError: true)
					def inv362 = new Invoice(job:sonicEsb, invoiceNumber:'362', deliveryMethod:'Email', deliveryStatus:'ToBeDelivered', other:'Mar 1 - 31, 2012').save(failOnError: true, flush:true)
					def line1 = new InvoiceLine(invoice:inv362, quantity:96.00F, itemName:'A&P:$100/hr', description:'Analysis & Programming Services', rate:100.00F, amount:9600.00F)
					line1.invoice = inv362
					line1.save(failOnError:true)
					def line2 = new InvoiceLine(invoice:inv362, quantity:1.00F, itemName:'Charm', description:'Charm and Wit', rate:100.00F, amount:100.00F)
					line2.invoice = inv362
					line2.save(failOnError:true)
					def to = new InvoiceRecipient(job:sonicEsb, type:'To', firstName:'Brian', lastName:'Schalme', email:'bschalme@airspeed.ca').save(failOnError: true)
				}
			}
		}
	}

	def destroy = {
	}
}
