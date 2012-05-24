
<%@ page import="ca.airspeed.invoice.Company" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-company" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-company" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'company.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="address1" title="${message(code: 'company.address1.label', default: 'Address1')}" />
					
						<g:sortableColumn property="address2" title="${message(code: 'company.address2.label', default: 'Address2')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'company.city.label', default: 'City')}" />
					
						<g:sortableColumn property="province" title="${message(code: 'company.province.label', default: 'Province')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'company.phone.label', default: 'Phone')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${companyInstanceList}" status="i" var="companyInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${companyInstance.id}">${fieldValue(bean: companyInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: companyInstance, field: "address1")}</td>
					
						<td>${fieldValue(bean: companyInstance, field: "address2")}</td>
					
						<td>${fieldValue(bean: companyInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: companyInstance, field: "province")}</td>
					
						<td>${fieldValue(bean: companyInstance, field: "phone")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${companyInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
