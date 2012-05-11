
<%@ page import="ca.airspeed.invoice.Invoice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-invoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-invoice" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="invoiceNumber" title="${message(code: 'invoice.invoiceNumber.label', default: 'Invoice Number')}" />
					
						<g:sortableColumn property="deliveryStatus" title="${message(code: 'invoice.deliveryStatus.label', default: 'Delivery Status')}" />
					
						<g:sortableColumn property="deliveryMethod" title="${message(code: 'invoice.deliveryMethod.label', default: 'Delivery Method')}" />
					
						<g:sortableColumn property="emailTemplateHtml" title="${message(code: 'invoice.emailTemplateHtml.label', default: 'Email Template Html')}" />
					
						<g:sortableColumn property="emailTemplatePlain" title="${message(code: 'invoice.emailTemplatePlain.label', default: 'Email Template Plain')}" />
					
						<th><g:message code="invoice.job.label" default="Job" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${invoiceInstanceList}" status="i" var="invoiceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${invoiceInstance.id}">${fieldValue(bean: invoiceInstance, field: "invoiceNumber")}</g:link></td>
					
						<td>${fieldValue(bean: invoiceInstance, field: "deliveryStatus")}</td>
					
						<td>${fieldValue(bean: invoiceInstance, field: "deliveryMethod")}</td>
					
						<td>${fieldValue(bean: invoiceInstance, field: "emailTemplateHtml")}</td>
					
						<td>${fieldValue(bean: invoiceInstance, field: "emailTemplatePlain")}</td>
					
						<td>${fieldValue(bean: invoiceInstance, field: "job")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${invoiceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
