<%@ page import="ca.airspeed.invoice.Attachment" %>



<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'fileName', 'error')} required">
	<label for="fileName">
		<g:message code="attachment.fileName.label" default="File Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fileName" required="" value="${attachmentInstance?.fileName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'mimeType', 'error')} ">
	<label for="mimeType">
		<g:message code="attachment.mimeType.label" default="Mime Type" />
		
	</label>
	<g:select name="mimeType" from="${attachmentInstance.constraints.mimeType.inList}" value="${attachmentInstance?.mimeType}" valueMessagePrefix="attachment.mimeType" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="attachment.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="content" name="content" />
</div>

<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'invoice', 'error')} required">
	<label for="invoice">
		<g:message code="attachment.invoice.label" default="Invoice" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="invoice" name="invoice.id" from="${ca.airspeed.invoice.Invoice.list()}" optionKey="id" required="" value="${attachmentInstance?.invoice?.id}" class="many-to-one"/>
</div>

