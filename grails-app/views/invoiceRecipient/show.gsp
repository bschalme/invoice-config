
<%@ page import="ca.airspeed.invoice.InvoiceRecipient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-invoiceRecipient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-invoiceRecipient" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list invoiceRecipient">
			
				<g:if test="${invoiceRecipientInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="invoiceRecipient.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${invoiceRecipientInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceRecipientInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="invoiceRecipient.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${invoiceRecipientInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceRecipientInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="invoiceRecipient.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${invoiceRecipientInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceRecipientInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="invoiceRecipient.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${invoiceRecipientInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceRecipientInstance?.job}">
				<li class="fieldcontain">
					<span id="job-label" class="property-label"><g:message code="invoiceRecipient.job.label" default="Job" /></span>
					
						<span class="property-value" aria-labelledby="job-label"><g:link controller="job" action="show" id="${invoiceRecipientInstance?.job?.id}">${invoiceRecipientInstance?.job?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${invoiceRecipientInstance?.id}" />
					<g:link class="edit" action="edit" id="${invoiceRecipientInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
