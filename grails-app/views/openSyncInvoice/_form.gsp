<%@ page import="ca.airspeed.invoice.OpenSyncInvoice" %>



<div class="fieldcontain ${hasErrors(bean: openSyncInvoiceInstance, field: 'customerJob', 'error')} ">
	<label for="customerJob">
		<g:message code="openSyncInvoice.customerJob.label" default="Customer Job" />
		
	</label>
	<g:textField name="customerJob" value="${openSyncInvoiceInstance?.customerJob}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: openSyncInvoiceInstance, field: 'invoiceDate', 'error')} required">
	<label for="invoiceDate">
		<g:message code="openSyncInvoice.invoiceDate.label" default="Invoice Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="invoiceDate" precision="day"  value="${openSyncInvoiceInstance?.invoiceDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: openSyncInvoiceInstance, field: 'invoiceNumber', 'error')} ">
	<label for="invoiceNumber">
		<g:message code="openSyncInvoice.invoiceNumber.label" default="Invoice Number" />
		
	</label>
	<g:textField name="invoiceNumber" value="${openSyncInvoiceInstance?.invoiceNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: openSyncInvoiceInstance, field: 'invoiceTotal', 'error')} required">
	<label for="invoiceTotal">
		<g:message code="openSyncInvoice.invoiceTotal.label" default="Invoice Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="invoiceTotal" required="" value="${fieldValue(bean: openSyncInvoiceInstance, field: 'invoiceTotal')}"/>
</div>

