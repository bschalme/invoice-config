<%@ page import="ca.airspeed.invoice.Job" %>



<div class="fieldcontain ${hasErrors(bean: jobInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="job.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="75" required="" value="${jobInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jobInstance, field: 'emailTemplateHtml', 'error')} required">
	<label for="emailTemplateHtml">
		<g:message code="job.emailTemplateHtml.label" default="Email Template Html" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="emailTemplateHtml" required="" value="${jobInstance?.emailTemplateHtml}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jobInstance, field: 'emailTemplatePlain', 'error')} required">
	<label for="emailTemplatePlain">
		<g:message code="job.emailTemplatePlain.label" default="Email Template Plain" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="emailTemplatePlain" required="" value="${jobInstance?.emailTemplatePlain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jobInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="job.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${ca.airspeed.invoice.Customer.list()}" optionKey="id" required="" value="${jobInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jobInstance, field: 'invoice', 'error')} ">
	<label for="invoice">
		<g:message code="job.invoice.label" default="Invoice" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${jobInstance?.invoice?}" var="i">
    <li><g:link controller="invoice" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="invoice" action="create" params="['job.id': jobInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'invoice.label', default: 'Invoice')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: jobInstance, field: 'invoiceRecipient', 'error')} ">
	<label for="invoiceRecipient">
		<g:message code="job.invoiceRecipient.label" default="Invoice Recipient" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${jobInstance?.invoiceRecipient?}" var="i">
    <li><g:link controller="invoiceRecipient" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="invoiceRecipient" action="create" params="['job.id': jobInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient')])}</g:link>
</li>
</ul>

</div>

