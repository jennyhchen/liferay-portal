<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

long powwowServerId = ParamUtil.getLong(request, "powwowServerId");

PowwowServer powwowServer = PowwowServerLocalServiceUtil.fetchPowwowServer(powwowServerId);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= powwowServer == null %>"
	title='<%= (powwowServer != null) ? powwowServer.getName() : "new-server" %>'
/>

<liferay-portlet:actionURL name="updatePowwowServer" var="editURL" />

<aui:form action="<%= editURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="powwowServerId" type="hidden" value="<%= String.valueOf(powwowServerId) %>" />

	<aui:model-context bean="<%= powwowServer %>" model="<%= PowwowServer.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input cssClass="optional-field" label="api-key" name="apiKey" />

		<aui:input cssClass="optional-field" name="secret" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>