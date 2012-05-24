
<%@ page import="ca.airspeed.invoice.Company" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-company" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-company" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list company">
			
				<g:if test="${companyInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="company.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${companyInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.address1}">
				<li class="fieldcontain">
					<span id="address1-label" class="property-label"><g:message code="company.address1.label" default="Address1" /></span>
					
						<span class="property-value" aria-labelledby="address1-label"><g:fieldValue bean="${companyInstance}" field="address1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.address2}">
				<li class="fieldcontain">
					<span id="address2-label" class="property-label"><g:message code="company.address2.label" default="Address2" /></span>
					
						<span class="property-value" aria-labelledby="address2-label"><g:fieldValue bean="${companyInstance}" field="address2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="company.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${companyInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.province}">
				<li class="fieldcontain">
					<span id="province-label" class="property-label"><g:message code="company.province.label" default="Province" /></span>
					
						<span class="property-value" aria-labelledby="province-label"><g:fieldValue bean="${companyInstance}" field="province"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="company.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${companyInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="company.url.label" default="Url" /></span>
					
						<span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${companyInstance}" field="url"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.invoiceFirstName}">
				<li class="fieldcontain">
					<span id="invoiceFirstName-label" class="property-label"><g:message code="company.invoiceFirstName.label" default="Invoice First Name" /></span>
					
						<span class="property-value" aria-labelledby="invoiceFirstName-label"><g:fieldValue bean="${companyInstance}" field="invoiceFirstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.invoiceLastName}">
				<li class="fieldcontain">
					<span id="invoiceLastName-label" class="property-label"><g:message code="company.invoiceLastName.label" default="Invoice Last Name" /></span>
					
						<span class="property-value" aria-labelledby="invoiceLastName-label"><g:fieldValue bean="${companyInstance}" field="invoiceLastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.invoiceEmail}">
				<li class="fieldcontain">
					<span id="invoiceEmail-label" class="property-label"><g:message code="company.invoiceEmail.label" default="Invoice Email" /></span>
					
						<span class="property-value" aria-labelledby="invoiceEmail-label"><g:fieldValue bean="${companyInstance}" field="invoiceEmail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.customer}">
				<li class="fieldcontain">
					<span id="customer-label" class="property-label"><g:message code="company.customer.label" default="Customer" /></span>
					
						<g:each in="${companyInstance.customer}" var="c">
						<span class="property-value" aria-labelledby="customer-label"><g:link controller="customer" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${companyInstance?.postalCode}">
				<li class="fieldcontain">
					<span id="postalCode-label" class="property-label"><g:message code="company.postalCode.label" default="Postal Code" /></span>
					
						<span class="property-value" aria-labelledby="postalCode-label"><g:fieldValue bean="${companyInstance}" field="postalCode"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${companyInstance?.id}" />
					<g:link class="edit" action="edit" id="${companyInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
