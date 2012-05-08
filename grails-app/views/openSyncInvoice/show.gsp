
<%@ page import="ca.airspeed.invoice.OpenSyncInvoice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'openSyncInvoice.label', default: 'OpenSyncInvoice')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-openSyncInvoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-openSyncInvoice" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list openSyncInvoice">
			
				<g:if test="${openSyncInvoiceInstance?.customerJob}">
				<li class="fieldcontain">
					<span id="customerJob-label" class="property-label"><g:message code="openSyncInvoice.customerJob.label" default="Customer Job" /></span>
					
						<span class="property-value" aria-labelledby="customerJob-label"><g:fieldValue bean="${openSyncInvoiceInstance}" field="customerJob"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${openSyncInvoiceInstance?.invoiceDate}">
				<li class="fieldcontain">
					<span id="invoiceDate-label" class="property-label"><g:message code="openSyncInvoice.invoiceDate.label" default="Invoice Date" /></span>
					
						<span class="property-value" aria-labelledby="invoiceDate-label"><g:formatDate date="${openSyncInvoiceInstance?.invoiceDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${openSyncInvoiceInstance?.invoiceNumber}">
				<li class="fieldcontain">
					<span id="invoiceNumber-label" class="property-label"><g:message code="openSyncInvoice.invoiceNumber.label" default="Invoice Number" /></span>
					
						<span class="property-value" aria-labelledby="invoiceNumber-label"><g:fieldValue bean="${openSyncInvoiceInstance}" field="invoiceNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${openSyncInvoiceInstance?.invoiceTotal}">
				<li class="fieldcontain">
					<span id="invoiceTotal-label" class="property-label"><g:message code="openSyncInvoice.invoiceTotal.label" default="Invoice Total" /></span>
					
						<span class="property-value" aria-labelledby="invoiceTotal-label"><g:fieldValue bean="${openSyncInvoiceInstance}" field="invoiceTotal"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
