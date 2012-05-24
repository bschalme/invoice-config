
<%@ page import="ca.airspeed.invoice.Job" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'job.label', default: 'Job')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-job" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-job" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list job">
			
				<g:if test="${jobInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="job.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${jobInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jobInstance?.emailTemplateHtml}">
				<li class="fieldcontain">
					<span id="emailTemplateHtml-label" class="property-label"><g:message code="job.emailTemplateHtml.label" default="Email Template Html" /></span>
					
						<span class="property-value" aria-labelledby="emailTemplateHtml-label"><g:fieldValue bean="${jobInstance}" field="emailTemplateHtml"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jobInstance?.emailTemplatePlain}">
				<li class="fieldcontain">
					<span id="emailTemplatePlain-label" class="property-label"><g:message code="job.emailTemplatePlain.label" default="Email Template Plain" /></span>
					
						<span class="property-value" aria-labelledby="emailTemplatePlain-label"><g:fieldValue bean="${jobInstance}" field="emailTemplatePlain"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jobInstance?.customer}">
				<li class="fieldcontain">
					<span id="customer-label" class="property-label"><g:message code="job.customer.label" default="Customer" /></span>
					
						<span class="property-value" aria-labelledby="customer-label"><g:link controller="customer" action="show" id="${jobInstance?.customer?.id}">${jobInstance?.customer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${jobInstance?.invoice}">
				<li class="fieldcontain">
					<span id="invoice-label" class="property-label"><g:message code="job.invoice.label" default="Invoice" /></span>
					
						<g:each in="${jobInstance.invoice}" var="i">
						<span class="property-value" aria-labelledby="invoice-label"><g:link controller="invoice" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${jobInstance?.invoiceRecipient}">
				<li class="fieldcontain">
					<span id="invoiceRecipient-label" class="property-label"><g:message code="job.invoiceRecipient.label" default="Invoice Recipient" /></span>
					
						<g:each in="${jobInstance.invoiceRecipient}" var="i">
						<span class="property-value" aria-labelledby="invoiceRecipient-label"><g:link controller="invoiceRecipient" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${jobInstance?.id}" />
					<g:link class="edit" action="edit" id="${jobInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
