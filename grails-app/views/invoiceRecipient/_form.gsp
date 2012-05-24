<%@ page import="ca.airspeed.invoice.InvoiceRecipient" %>



<div class="fieldcontain ${hasErrors(bean: invoiceRecipientInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="invoiceRecipient.type.label" default="Type" />
		
	</label>
	<g:select name="type" from="${invoiceRecipientInstance.constraints.type.inList}" value="${invoiceRecipientInstance?.type}" valueMessagePrefix="invoiceRecipient.type" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceRecipientInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="invoiceRecipient.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${invoiceRecipientInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceRecipientInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="invoiceRecipient.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${invoiceRecipientInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceRecipientInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="invoiceRecipient.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${invoiceRecipientInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceRecipientInstance, field: 'job', 'error')} required">
	<label for="job">
		<g:message code="invoiceRecipient.job.label" default="Job" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="job" name="job.id" from="${ca.airspeed.invoice.Job.list()}" optionKey="id" required="" value="${invoiceRecipientInstance?.job?.id}" class="many-to-one"/>
</div>

