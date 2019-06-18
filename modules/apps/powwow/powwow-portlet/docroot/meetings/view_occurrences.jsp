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
String backURL = ParamUtil.getString(request, "backURL");
long powwowMeetingId = ParamUtil.getLong(request, "powwowMeetingId");
TimeZone userTimeZone = TimeZone.getTimeZone(user.getTimeZoneId());

PowwowMeeting powwowMeeting = PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(powwowMeetingId);

portletURL.setParameter("jspPage", "/meetings/view_occurrences.jsp");
portletURL.setParameter("powwowMeetingId", String.valueOf(powwowMeetingId));
portletURL.setParameter("backURL", backURL);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="meeting-occurrences"
/>

<div class="meeting-container">
	<div id="<portlet:namespace />errorMessage"></div>

	<%
	boolean displayMeetingActions = false;
	%>

	<%@ include file="/meetings/meeting_body.jspf" %>

	<c:if test="<%= calendarBooking.isRecurring() %>">
		<div class="recurrence">
			<dt>
				<liferay-ui:message key="repeat" />
			</dt>
			<dd>
				<span>
					<%= PowwowUtil.getRecurrenceSummary(calendarBooking.getRecurrenceObj(), locale) %>
				</span>
			</dd>
		</div>
	</c:if>

	<div class="occurrences">
		<dt>
			<liferay-ui:message key="occurrences" />
		</dt>
		<dd>
			Placeholder

			<!-- TODO display occurrences -->

			<portlet:renderURL var="editOccurrenceURL">
				<portlet:param name="mvcPath" value="/meetings/edit_occurrence.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="powwowMeetingId" value="<%= String.valueOf(powwowMeeting.getPowwowMeetingId()) %>" />
			</portlet:renderURL>

			<aui:button onClick="<%= editOccurrenceURL.toString() %>" value="edit" />

		</dd>
	</div>
</div>