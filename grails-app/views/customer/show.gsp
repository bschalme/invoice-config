
<%@ page import="ca.airspeed.invoice.Customer" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-customer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-customer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list customer">
			
				<g:if test="${customerInstance?.fullName}">
				<li class="fieldcontain">
					<span id="fullName-label" class="property-label"><g:message code="customer.fullName.label" default="Full Name" /></span>
					
						<span class="property-value" aria-labelledby="fullName-label"><g:fieldValue bean="${customerInstance}" field="fullName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.defaultDeliveryMethod}">
				<li class="fieldcontain">
					<span id="defaultDeliveryMethod-label" class="property-label"><g:message code="customer.defaultDeliveryMethod.label" default="Default Delivery Method" /></span>
					
						<span class="property-value" aria-labelledby="defaultDeliveryMethod-label"><g:fieldValue bean="${customerInstance}" field="defaultDeliveryMethod"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.company}">
				<li class="fieldcontain">
					<span id="company-label" class="property-label"><g:message code="customer.company.label" default="Company" /></span>
					
						<span class="property-value" aria-labelledby="company-label"><g:link controller="company" action="show" id="${customerInstance?.company?.id}">${customerInstance?.company?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.customerRefListId}">
				<li class="fieldcontain">
					<span id="customerRefListId-label" class="property-label"><g:message code="customer.customerRefListId.label" default="Customer Ref List Id" /></span>
					
						<span class="property-value" aria-labelledby="customerRefListId-label"><g:fieldValue bean="${customerInstance}" field="customerRefListId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.invoiceRecipient}">
				<li class="fieldcontain">
					<span id="invoiceRecipient-label" class="property-label"><g:message code="customer.invoiceRecipient.label" default="Invoice Recipient" /></span>
					
						<g:each in="${customerInstance.invoiceRecipient}" var="i">
						<span class="property-value" aria-labelledby="invoiceRecipient-label"><g:link controller="invoiceRecipient" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.job}">
				<li class="fieldcontain">
					<span id="job-label" class="property-label"><g:message code="customer.job.label" default="Job" /></span>
					
						<g:each in="${customerInstance.job}" var="j">
						<span class="property-value" aria-labelledby="job-label"><g:link controller="job" action="show" id="${j.id}">${j?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${customerInstance?.id}" />
					<g:link class="edit" action="edit" id="${customerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
