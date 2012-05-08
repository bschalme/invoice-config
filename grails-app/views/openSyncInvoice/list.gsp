
<%@ page import="ca.airspeed.invoice.OpenSyncInvoice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'openSyncInvoice.label', default: 'OpenSyncInvoice')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-openSyncInvoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-openSyncInvoice" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="customerJob" title="${message(code: 'openSyncInvoice.customerJob.label', default: 'Customer Job')}" />
					
						<g:sortableColumn property="invoiceDate" title="${message(code: 'openSyncInvoice.invoiceDate.label', default: 'Invoice Date')}" />
					
						<g:sortableColumn property="invoiceNumber" title="${message(code: 'openSyncInvoice.invoiceNumber.label', default: 'Invoice Number')}" />
					
						<g:sortableColumn property="invoiceTotal" title="${message(code: 'openSyncInvoice.invoiceTotal.label', default: 'Invoice Total')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${openSyncInvoiceInstanceList}" status="i" var="openSyncInvoiceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${openSyncInvoiceInstance.id}">${fieldValue(bean: openSyncInvoiceInstance, field: "customerJob")}</g:link></td>
					
						<td><g:formatDate date="${openSyncInvoiceInstance.invoiceDate}" /></td>
					
						<td>${fieldValue(bean: openSyncInvoiceInstance, field: "invoiceNumber")}</td>
					
						<td>${fieldValue(bean: openSyncInvoiceInstance, field: "invoiceTotal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${openSyncInvoiceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
