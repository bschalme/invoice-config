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

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'emailTemplatePlain', 'error')} ">
	<label for="emailTemplatePlain">
		<g:message code="invoice.emailTemplatePlain.label" default="Email Template Plain" />
		
	</label>
	<g:textField name="emailTemplatePlain" value="${invoiceInstance?.emailTemplatePlain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'other', 'error')} ">
	<label for="other">
		<g:message code="invoice.other.label" default="Other" />
		
	</label>
	<g:textField name="other" maxlength="30" value="${invoiceInstance?.other}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'attachments', 'error')} ">
	<label for="attachments">
		<g:message code="invoice.attachments.label" default="Attachments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${invoiceInstance?.attachments?}" var="a">
    <li><g:link controller="attachment" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="attachment" action="create" params="['invoice.id': invoiceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'attachment.label', default: 'Attachment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'invoiceLine', 'error')} ">
	<label for="invoiceLine">
		<g:message code="invoice.invoiceLine.label" default="Invoice Line" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${invoiceInstance?.invoiceLine?}" var="i">
    <li><g:link controller="invoiceLine" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="invoiceLine" action="create" params="['invoice.id': invoiceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'invoiceLine.label', default: 'InvoiceLine')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: invoiceInstance, field: 'job', 'error')} required">
	<label for="job">
		<g:message code="invoice.job.label" default="Job" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="job" name="job.id" from="${ca.airspeed.invoice.Job.list()}" optionKey="id" required="" value="${invoiceInstance?.job?.id}" class="many-to-one"/>
</div>

