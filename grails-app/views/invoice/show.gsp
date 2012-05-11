
<%@ page import="ca.airspeed.invoice.Invoice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-invoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-invoice" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list invoice">
			
				<g:if test="${invoiceInstance?.invoiceNumber}">
				<li class="fieldcontain">
					<span id="invoiceNumber-label" class="property-label"><g:message code="invoice.invoiceNumber.label" default="Invoice Number" /></span>
					
						<span class="property-value" aria-labelledby="invoiceNumber-label"><g:fieldValue bean="${invoiceInstance}" field="invoiceNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceInstance?.deliveryStatus}">
				<li class="fieldcontain">
					<span id="deliveryStatus-label" class="property-label"><g:message code="invoice.deliveryStatus.label" default="Delivery Status" /></span>
					
						<span class="property-value" aria-labelledby="deliveryStatus-label"><g:fieldValue bean="${invoiceInstance}" field="deliveryStatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceInstance?.deliveryMethod}">
				<li class="fieldcontain">
					<span id="deliveryMethod-label" class="property-label"><g:message code="invoice.deliveryMethod.label" default="Delivery Method" /></span>
					
						<span class="property-value" aria-labelledby="deliveryMethod-label"><g:fieldValue bean="${invoiceInstance}" field="deliveryMethod"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceInstance?.emailTemplateHtml}">
				<li class="fieldcontain">
					<span id="emailTemplateHtml-label" class="property-label"><g:message code="invoice.emailTemplateHtml.label" default="Email Template Html" /></span>
					
						<span class="property-value" aria-labelledby="emailTemplateHtml-label"><g:fieldValue bean="${invoiceInstance}" field="emailTemplateHtml"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceInstance?.emailTemplatePlain}">
				<li class="fieldcontain">
					<span id="emailTemplatePlain-label" class="property-label"><g:message code="invoice.emailTemplatePlain.label" default="Email Template Plain" /></span>
					
						<span class="property-value" aria-labelledby="emailTemplatePlain-label"><g:fieldValue bean="${invoiceInstance}" field="emailTemplatePlain"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceInstance?.invoiceLine}">
				<li class="fieldcontain">
					<span id="invoiceLine-label" class="property-label"><g:message code="invoice.invoiceLine.label" default="Invoice Line" /></span>
					
						<g:each in="${invoiceInstance.invoiceLine}" var="i">
						<span class="property-value" aria-labelledby="invoiceLine-label"><g:link controller="invoiceLine" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceInstance?.job}">
				<li class="fieldcontain">
					<span id="job-label" class="property-label"><g:message code="invoice.job.label" default="Job" /></span>
					
						<span class="property-value" aria-labelledby="job-label"><g:link controller="job" action="show" id="${invoiceInstance?.job?.id}">${invoiceInstance?.job?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${invoiceInstance?.id}" />
					<g:link class="edit" action="edit" id="${invoiceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:actionSubmit action="email" value="Email this Invoice" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
