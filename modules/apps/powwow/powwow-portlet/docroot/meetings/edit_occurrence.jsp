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

String occurrenceId = ParamUtil.getString(request, "occurrenceId");

PowwowMeetingOccurrence powwowMeetingOccurrence = 
	PowwowMeetingOccurrenceLocalServiceUtil.fetchPowwowMeetingOccurrence(occurrenceId);

long powwowMeetingId = powwowMeetingOccurrence.getPowwowMeetingId();

PowwowMeeting powwowMeeting = PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(powwowMeetingId);

long calendarBookingId = 0;

if (powwowMeeting != null) {
	calendarBookingId = powwowMeeting.getCalendarBookingId();
}

CalendarBooking calendarBooking = null;
if (calendarBookingId > 0) {
	calendarBooking = CalendarBookingServiceUtil.fetchCalendarBooking(calendarBookingId);
}
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='edit-occurrence'
/>

<div class="message-container-editing" id="<portlet:namespace />messageContainerEditing">
</div>

<liferay-portlet:actionURL name="updatePowwowMeetingOccurrence" var="updatePowwowMeetingOccurrenceURL" />

<aui:form action="<%= updatePowwowMeetingOccurrenceURL %>" method="post" name="fm">
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="powwowMeetingId" type="hidden" value="<%= String.valueOf(powwowMeetingId) %>" />
	<aui:input name="occurrenceId" type="hidden" value="<%= String.valueOf(occurrenceId) %>" />

	<aui:input cssClass="meeting-name" disabled="true" name="name" value="<%= powwowMeeting.getName() %>"/>

	<aui:input cssClass="" disabled="true" name="repeat" value="<%= PowwowUtil.getRecurrenceSummary(calendarBooking.getRecurrenceObj(), locale) %>"/>

	<label class="control-label" for="<portlet:namespace />meetingEventDate"><liferay-ui:message key="meeting-date" /></label>

	<div class="control-group meeting-event-date" id="<portlet:namespace />meetingEventDate">

		<%

		// TODO use occurrence's time

		Calendar startCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);
		Calendar endCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

		startCalendar.setTimeInMillis(powwowMeetingOccurrence.getStartTime());
		endCalendar.setTimeInMillis(powwowMeetingOccurrence.getEndTime());

		%>

		<span class="start-date-container" id="<portlet:namespace />startDateContainer">
			<liferay-ui:input-date
				dayParam="startTimeDay"
				dayValue="<%= startCalendar.get(Calendar.DATE) %>"
				disabled="<%= false %>"
				firstDayOfWeek="<%= startCalendar.getFirstDayOfWeek() - 1 %>"
				monthParam="startTimeMonth"
				monthValue="<%= startCalendar.get(Calendar.MONTH) %>"
				name="startDate"
				yearParam="startTimeYear"
				yearValue="<%= startCalendar.get(Calendar.YEAR) %>"
			/>

			<liferay-ui:input-time
				amPmParam="startTimeAmPm"
				amPmValue="<%= startCalendar.get(Calendar.AM_PM) %>"
				dateParam="startDateTime"
				dateValue="<%= startCalendar.getTime() %>"
				disabled="<%= false %>"
				hourParam="startTimeHour"
				hourValue="<%= startCalendar.get(Calendar.HOUR) %>"
				minuteParam="startTimeMinute"
				minuteValue="<%= startCalendar.get(Calendar.MINUTE) %>"
				name="startTime"
			/>
		</span>
		<span class="to"><liferay-ui:message key="to" /></span>

		<span class="end-date-container" id="<portlet:namespace />endDateContainer">
			<liferay-ui:input-date
				dayParam="endTimeDay"
				dayValue="<%= endCalendar.get(Calendar.DATE) %>"
				disabled="<%= false %>"
				firstDayOfWeek="<%= endCalendar.getFirstDayOfWeek() - 1 %>"
				monthParam="endTimeMonth"
				monthValue="<%= endCalendar.get(Calendar.MONTH) %>"
				name="endDate"
				yearParam="endTimeYear"
				yearValue="<%= endCalendar.get(Calendar.YEAR) %>"
			/>

			<liferay-ui:input-time
				amPmParam="endTimeAmPm"
				amPmValue="<%= endCalendar.get(Calendar.AM_PM) %>"
				dateParam="endDateTime"
				dateValue="<%= endCalendar.getTime() %>"
				disabled="<%= false %>"
				hourParam="endTimeHour"
				hourValue="<%= endCalendar.get(Calendar.HOUR) %>"
				minuteParam="endTimeMinute"
				minuteValue="<%= endCalendar.get(Calendar.MINUTE) %>"
				name="endTime"
			/>
		</span>
	</div>

	<aui:button-row>
		<aui:button name="submit" type="submit" />

		<aui:button onClick="<%= backURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,aui-form-validator,aui-io-request,liferay-plugin-meeting-scheduler,liferay-plugin-meeting-util">
	new Liferay.MeetingScheduler(
		{
			containerId: 'meetingEventDate',
			endDatePickerName: 'endDate',
			endTimePickerName: 'endTime',
			namespace: '<portlet:namespace />',
			startDatePickerName: 'startDate',
			startTimePickerName: 'startTime',
			submitButtonId: 'submit'
		}
	);
</aui:script>