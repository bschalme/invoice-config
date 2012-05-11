<%@ page import="ca.airspeed.invoice.Invoice" %>



<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'invoiceNumber', 'error')} required">
	<label for="invoiceNumber">
		<g:message code="invoice.invoiceNumber.label" default="Invoice Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="invoiceNumber" required="" value="${invoiceInstance?.invoiceNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'deliveryStatus', 'error')} required">
	<label for="deliveryStatus">
		<g:message code="invoice.deliveryStatus.label" default="Delivery Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="deliveryStatus" from="${invoiceInstance.constraints.deliveryStatus.inList}" required="" value="${invoiceInstance?.deliveryStatus}" valueMessagePrefix="invoice.deliveryStatus"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'deliveryMethod', 'error')} required">
	<label for="deliveryMethod">
		<g:message code="invoice.deliveryMethod.label" default="Delivery Method" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="deliveryMethod" from="${invoiceInstance.constraints.deliveryMethod.inList}" required="" value="${invoiceInstance?.deliveryMethod}" valueMessagePrefix="invoice.deliveryMethod"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'emailTemplateHtml', 'error')} ">
	<label for="emailTemplateHtml">
		<g:message code="invoice.emailTemplateHtml.label" default="Email Template Html" />
		
	</label>
	<g:textField name="emailTemplateHtml" value="${invoiceInstance?.emailTemplateHtml}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'emailTemplatePlain', 'error')} required">
	<label for="emailTemplatePlain">
		<g:message code="invoice.emailTemplatePlain.label" default="Email Template Plain" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="emailTemplatePlain" required="" value="${invoiceInstance?.emailTemplatePlain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'invoiceLine', 'error')} ">
	<label for="invoiceLine">
		<g:message code="invoice.invoiceLine.label" default="Invoice Line" />
		
	</label>
	<g:select name="invoiceLine" from="${ca.airspeed.invoice.InvoiceLine.list()}" multiple="multiple" optionKey="id" size="5" value="${invoiceInstance?.invoiceLine*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'job', 'error')} required">
	<label for="job">
		<g:message code="invoice.job.label" default="Job" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="job" name="job.id" from="${ca.airspeed.invoice.Job.list()}" optionKey="id" required="" value="${invoiceInstance?.job?.id}" class="many-to-one"/>
</div>

