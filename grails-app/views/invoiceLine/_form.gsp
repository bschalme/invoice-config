<%@ page import="ca.airspeed.invoice.InvoiceLine" %>



<div class="fieldcontain ${hasErrors(bean: invoiceLineInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="invoiceLine.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" step="0.01" min="0.0" max="10000.0" required="" value="${fieldValue(bean: invoiceLineInstance, field: 'quantity')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceLineInstance, field: 'itemName', 'error')} required">
	<label for="itemName">
		<g:message code="invoiceLine.itemName.label" default="Item Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="itemName" maxlength="25" required="" value="${invoiceLineInstance?.itemName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceLineInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="invoiceLine.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="65" required="" value="${invoiceLineInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceLineInstance, field: 'rate', 'error')} required">
	<label for="rate">
		<g:message code="invoiceLine.rate.label" default="Rate" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="rate" step="0.01" min="0.0" max="999999.9" required="" value="${fieldValue(bean: invoiceLineInstance, field: 'rate')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceLineInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="invoiceLine.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" step="0.01" min="0.0" max="1.0E7" required="" value="${fieldValue(bean: invoiceLineInstance, field: 'amount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: invoiceLineInstance, field: 'invoice', 'error')} required">
	<label for="invoice">
		<g:message code="invoiceLine.invoice.label" default="Invoice" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="invoice" name="invoice.id" from="${ca.airspeed.invoice.Invoice.list()}" optionKey="id" required="" value="${invoiceLineInstance?.invoice?.id}" class="many-to-one"/>
</div>

