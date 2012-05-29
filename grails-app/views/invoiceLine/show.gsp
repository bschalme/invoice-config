
<%@ page import="ca.airspeed.invoice.InvoiceLine" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'invoiceLine.label', default: 'InvoiceLine')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-invoiceLine" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-invoiceLine" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list invoiceLine">
			
				<g:if test="${invoiceLineInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="invoiceLine.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${invoiceLineInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceLineInstance?.itemName}">
				<li class="fieldcontain">
					<span id="itemName-label" class="property-label"><g:message code="invoiceLine.itemName.label" default="Item Name" /></span>
					
						<span class="property-value" aria-labelledby="itemName-label"><g:fieldValue bean="${invoiceLineInstance}" field="itemName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceLineInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="invoiceLine.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${invoiceLineInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceLineInstance?.rate}">
				<li class="fieldcontain">
					<span id="rate-label" class="property-label"><g:message code="invoiceLine.rate.label" default="Rate" /></span>
					
						<span class="property-value" aria-labelledby="rate-label"><g:fieldValue bean="${invoiceLineInstance}" field="rate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceLineInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="invoiceLine.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${invoiceLineInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${invoiceLineInstance?.invoice}">
				<li class="fieldcontain">
					<span id="invoice-label" class="property-label"><g:message code="invoiceLine.invoice.label" default="Invoice" /></span>
					
						<span class="property-value" aria-labelledby="invoice-label"><g:link controller="invoice" action="show" id="${invoiceLineInstance?.invoice?.id}">${invoiceLineInstance?.invoice?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${invoiceLineInstance?.id}" />
					<g:link class="edit" action="edit" id="${invoiceLineInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
