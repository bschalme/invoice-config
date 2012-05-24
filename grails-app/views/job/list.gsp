
<%@ page import="ca.airspeed.invoice.Job" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'job.label', default: 'Job')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-job" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-job" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'job.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="emailTemplateHtml" title="${message(code: 'job.emailTemplateHtml.label', default: 'Email Template Html')}" />
					
						<g:sortableColumn property="emailTemplatePlain" title="${message(code: 'job.emailTemplatePlain.label', default: 'Email Template Plain')}" />
					
						<th><g:message code="job.customer.label" default="Customer" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${jobInstanceList}" status="i" var="jobInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${jobInstance.id}">${fieldValue(bean: jobInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: jobInstance, field: "emailTemplateHtml")}</td>
					
						<td>${fieldValue(bean: jobInstance, field: "emailTemplatePlain")}</td>
					
						<td>${fieldValue(bean: jobInstance, field: "customer")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${jobInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
