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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

PowwowMeetingOccurrence powwowMeetingOccurrence = (PowwowMeetingOccurrence) row.getObject();

long powwowMeetingId = powwowMeetingOccurrence.getPowwowMeetingId();

%>

<liferay-ui:icon-menu>

	<c:if test="<%= !powwowMeetingOccurrence.isEndTimePassed() %>">
		<portlet:renderURL var="editOccurrenceURL">
			<portlet:param name="mvcPath" value="/meetings/edit_occurrence.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="occurrenceId" value="<%= String.valueOf(powwowMeetingOccurrence.getOccurrenceId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-edit"
			label="<%= true %>"
			message='edit'
			url="<%= editOccurrenceURL %>"
		/>
	</c:if>

	<c:if test="<%= !OccurrenceStatus.DELETE.equals(powwowMeetingOccurrence.getOccurrenceStatusEnum()) %>">
		<liferay-ui:icon
			iconCssClass="icon-remove"
			label="<%= true %>"
			message='delete'
			onClick='<%= renderResponse.getNamespace() + "deleteOccurrence(" + String.valueOf(powwowMeetingOccurrence.getOccurrenceId()) + ");" %>'
			url="javascript:;"
		/>
	</c:if>
</liferay-ui:icon-menu>

<portlet:renderURL var="occurrencesViewURL" windowState="<%=LiferayWindowState.NORMAL.toString() %>">
	<portlet:param name="mvcPath" value="/meetings/view_occurrences.jsp" />
	<portlet:param name="powwowMeetingId" value="<%= String.valueOf(powwowMeetingId) %>" />
</portlet:renderURL>

<aui:script use="aui-io-request,aui-base">

	window
		.<portlet:namespace />displayError =
			function(message) {
				var errorMessage = AUI().one('#<portlet:namespace />errorMessage');

				if (message) {
					errorMessage.html('<div class="alert alert-error">' + message + '</div>');
				}
			}

	window
		.<portlet:namespace/>deleteOccurrence =
			function(occurrenceId){
				if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-occurrence") %>')) {
					var uri = '<portlet:actionURL name="deleteOccurrence"></portlet:actionURL>';

					uri = Liferay.Util.addParams('<portlet:namespace />occurrenceId=' + occurrenceId, uri);

					A.io.request(
						uri,
						{
							after: {
								success: function() {
									var responseData = this.get('responseData');

									if (responseData.success) {
										document.location.href = '<%= occurrencesViewURL.toString() %>';
									}
									else {
										<portlet:namespace />displayError(responseData.message);
									}

								}
							},
							dataType: 'JSON'
						}
					);
				}
	}
</aui:script>
