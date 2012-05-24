<%@ page import="ca.airspeed.invoice.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'fullName', 'error')} ">
	<label for="fullName">
		<g:message code="customer.fullName.label" default="Full Name" />
		
	</label>
	<g:textField name="fullName" value="${customerInstance?.fullName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'defaultDeliveryMethod', 'error')} required">
	<label for="defaultDeliveryMethod">
		<g:message code="customer.defaultDeliveryMethod.label" default="Default Delivery Method" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="defaultDeliveryMethod" from="${customerInstance.constraints.defaultDeliveryMethod.inList}" required="" value="${customerInstance?.defaultDeliveryMethod}" valueMessagePrefix="customer.defaultDeliveryMethod"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'company', 'error')} required">
	<label for="company">
		<g:message code="customer.company.label" default="Company" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="company" name="company.id" from="${ca.airspeed.invoice.Company.list()}" optionKey="id" required="" value="${customerInstance?.company?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'customerRefListId', 'error')} ">
	<label for="customerRefListId">
		<g:message code="customer.customerRefListId.label" default="Customer Ref List Id" />
		
	</label>
	<g:textField name="customerRefListId" value="${customerInstance?.customerRefListId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'invoiceRecipient', 'error')} ">
	<label for="invoiceRecipient">
		<g:message code="customer.invoiceRecipient.label" default="Invoice Recipient" />
		
	</label>
	<g:select name="invoiceRecipient" from="${ca.airspeed.invoice.InvoiceRecipient.list()}" multiple="multiple" optionKey="id" size="5" value="${customerInstance?.invoiceRecipient*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'job', 'error')} ">
	<label for="job">
		<g:message code="customer.job.label" default="Job" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${customerInstance?.job?}" var="j">
    <li><g:link controller="job" action="show" id="${j.id}">${j?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="job" action="create" params="['customer.id': customerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'job.label', default: 'Job')])}</g:link>
</li>
</ul>

</div>

