
<%@ page import="ca.airspeed.invoice.InvoiceRecipient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'invoiceRecipient.label', default: 'InvoiceRecipient')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-invoiceRecipient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-invoiceRecipient" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="type" title="${message(code: 'invoiceRecipient.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'invoiceRecipient.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'invoiceRecipient.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'invoiceRecipient.email.label', default: 'Email')}" />
					
						<th><g:message code="invoiceRecipient.job.label" default="Job" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${invoiceRecipientInstanceList}" status="i" var="invoiceRecipientInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${invoiceRecipientInstance.id}">${fieldValue(bean: invoiceRecipientInstance, field: "type")}</g:link></td>
					
						<td>${fieldValue(bean: invoiceRecipientInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: invoiceRecipientInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: invoiceRecipientInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: invoiceRecipientInstance, field: "job")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${invoiceRecipientInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
