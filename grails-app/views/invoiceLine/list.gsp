
<%@ page import="ca.airspeed.invoice.InvoiceLine" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'invoiceLine.label', default: 'InvoiceLine')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-invoiceLine" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-invoiceLine" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="quantity" title="${message(code: 'invoiceLine.quantity.label', default: 'Quantity')}" />
					
						<g:sortableColumn property="itemName" title="${message(code: 'invoiceLine.itemName.label', default: 'Item Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'invoiceLine.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="rate" title="${message(code: 'invoiceLine.rate.label', default: 'Rate')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'invoiceLine.amount.label', default: 'Amount')}" />
					
						<th><g:message code="invoiceLine.invoice.label" default="Invoice" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${invoiceLineInstanceList}" status="i" var="invoiceLineInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${invoiceLineInstance.id}">${fieldValue(bean: invoiceLineInstance, field: "quantity")}</g:link></td>
					
						<td>${fieldValue(bean: invoiceLineInstance, field: "itemName")}</td>
					
						<td>${fieldValue(bean: invoiceLineInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: invoiceLineInstance, field: "rate")}</td>
					
						<td>${fieldValue(bean: invoiceLineInstance, field: "amount")}</td>
					
						<td>${fieldValue(bean: invoiceLineInstance, field: "invoice")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${invoiceLineInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
