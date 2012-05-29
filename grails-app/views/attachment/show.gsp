
<%@ page import="ca.airspeed.invoice.Attachment" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'attachment.label', default: 'Attachment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-attachment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-attachment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list attachment">
			
				<g:if test="${attachmentInstance?.fileName}">
				<li class="fieldcontain">
					<span id="fileName-label" class="property-label"><g:message code="attachment.fileName.label" default="File Name" /></span>
					
						<span class="property-value" aria-labelledby="fileName-label"><g:fieldValue bean="${attachmentInstance}" field="fileName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${attachmentInstance?.mimeType}">
				<li class="fieldcontain">
					<span id="mimeType-label" class="property-label"><g:message code="attachment.mimeType.label" default="Mime Type" /></span>
					
						<span class="property-value" aria-labelledby="mimeType-label"><g:fieldValue bean="${attachmentInstance}" field="mimeType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${attachmentInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="attachment.content.label" default="Content" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${attachmentInstance?.invoice}">
				<li class="fieldcontain">
					<span id="invoice-label" class="property-label"><g:message code="attachment.invoice.label" default="Invoice" /></span>
					
						<span class="property-value" aria-labelledby="invoice-label"><g:link controller="invoice" action="show" id="${attachmentInstance?.invoice?.id}">${attachmentInstance?.invoice?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${attachmentInstance?.id}" />
					<g:link class="edit" action="edit" id="${attachmentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
