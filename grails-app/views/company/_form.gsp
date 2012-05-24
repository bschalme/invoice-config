<%@ page import="ca.airspeed.invoice.Company" %>



<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="company.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${companyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'address1', 'error')} required">
	<label for="address1">
		<g:message code="company.address1.label" default="Address1" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address1" maxlength="25" required="" value="${companyInstance?.address1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'address2', 'error')} ">
	<label for="address2">
		<g:message code="company.address2.label" default="Address2" />
		
	</label>
	<g:textField name="address2" maxlength="25" value="${companyInstance?.address2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="company.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" maxlength="25" required="" value="${companyInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'province', 'error')} required">
	<label for="province">
		<g:message code="company.province.label" default="Province" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="province" required="" value="${companyInstance?.province}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="company.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phone" maxlength="30" required="" value="${companyInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="company.url.label" default="Url" />
		
	</label>
	<g:field type="url" name="url" value="${companyInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'invoiceFirstName', 'error')} required">
	<label for="invoiceFirstName">
		<g:message code="company.invoiceFirstName.label" default="Invoice First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="invoiceFirstName" maxlength="25" required="" value="${companyInstance?.invoiceFirstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'invoiceLastName', 'error')} ">
	<label for="invoiceLastName">
		<g:message code="company.invoiceLastName.label" default="Invoice Last Name" />
		
	</label>
	<g:textField name="invoiceLastName" maxlength="25" value="${companyInstance?.invoiceLastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'invoiceEmail', 'error')} required">
	<label for="invoiceEmail">
		<g:message code="company.invoiceEmail.label" default="Invoice Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="invoiceEmail" required="" value="${companyInstance?.invoiceEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'customer', 'error')} ">
	<label for="customer">
		<g:message code="company.customer.label" default="Customer" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${companyInstance?.customer?}" var="c">
    <li><g:link controller="customer" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="customer" action="create" params="['company.id': companyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'customer.label', default: 'Customer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'postalCode', 'error')} ">
	<label for="postalCode">
		<g:message code="company.postalCode.label" default="Postal Code" />
		
	</label>
	<g:textField name="postalCode" value="${companyInstance?.postalCode}"/>
</div>

