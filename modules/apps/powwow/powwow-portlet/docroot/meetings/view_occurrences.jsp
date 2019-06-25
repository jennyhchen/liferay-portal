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
if (Validator.isNull(backURL)) {
	backURL = ParamUtil.getString(request, "redirect");
}
long powwowMeetingId = ParamUtil.getLong(request, "powwowMeetingId");
boolean showAll = ParamUtil.getBoolean(request, "all", false);
TimeZone userTimeZone = TimeZone.getTimeZone(user.getTimeZoneId());

PowwowMeeting powwowMeeting = PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(powwowMeetingId);

portletURL.setParameter("jspPage", "/meetings/view_occurrences.jsp");
portletURL.setParameter("powwowMeetingId", String.valueOf(powwowMeetingId));
portletURL.setParameter("all", String.valueOf(showAll));
portletURL.setParameter("backURL", backURL);

List<PowwowMeetingOccurrence> powwowMeetingOccurrences =
	PowwowMeetingOccurrenceLocalServiceUtil.findByPowwowMeetingId(powwowMeetingId);

if (!showAll) {

	// filter only available occurrences

	powwowMeetingOccurrences = ListUtil.filter(powwowMeetingOccurrences,
		new PredicateFilter<PowwowMeetingOccurrence>() {

			@Override
			public boolean filter(PowwowMeetingOccurrence t) {

				return OccurrenceStatus.AVAILABLE.equals(
					t.getOccurrenceStatusEnum()) && !t.isEndTimePassed();
			}
		});
}

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

			<portlet:renderURL var="viewOccurrencesRenderURL"/>
			<aui:form action="${viewOccurrencesRenderURL}" id="fmViewOccurrences" method="post" name="fmViewOccurrences" onSubmit="event.preventDefault();">
				<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
				<aui:input name="jspPage" type="hidden" value="/meetings/view_occurrences.jsp" />
				<aui:input name="powwowMeetingId" type="hidden" value="<%= String.valueOf(powwowMeetingId) %>" />

				<aui:field-wrapper inlineField="<%= true %>" label="">
					<aui:input checked="<%= showAll %>" label="show-all" name="all" type="checkbox" onChange="submitForm(this.form)"/>
				</aui:field-wrapper>
			</aui:form>
		</dt>
		<dd>
			<liferay-ui:search-container
				emptyResultsMessage="no-available-occurrences"
				iteratorURL="<%= portletURL %>"
				total="<%= powwowMeetingOccurrences.size() %>"
			>

				<liferay-ui:search-container-results
					results="<%= ListUtil.subList(powwowMeetingOccurrences, searchContainer.getStart(), searchContainer.getEnd()) %>" />

				<liferay-ui:search-container-row
					className="com.liferay.powwow.model.PowwowMeetingOccurrence"
					escapedModel="<%= true %>"
					keyProperty="occurrenceId"
					modelVar="powwowMeetingOccurrence"
				>
					<%
						Calendar startTimeCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);
						Calendar endTimeCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

						startTimeCalendar.setTimeInMillis(powwowMeetingOccurrence.getStartTime());
						endTimeCalendar.setTimeInMillis(powwowMeetingOccurrence.getEndTime());

						String startTime = occurrenceTimeFormat.format(startTimeCalendar.getTime());
						String endTime = occurrenceTimeFormat.format(endTimeCalendar.getTime());
					%>
					<liferay-ui:search-container-column-text
						name="start-time"
						value="<%= startTime %>"
					/>

					<liferay-ui:search-container-column-text
						name="end-time"
						value="<%= endTime %>"
					/>

					<liferay-ui:search-container-column-text name="status" >
						<c:choose>
							<c:when test="${powwowMeetingOccurrence.occurrenceStatus eq 'available' and powwowMeetingOccurrence.isEndTimePassed()}">
								<liferay-ui:message key="completed" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="${powwowMeetingOccurrence.occurrenceStatus}" />
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						path="/meetings/occurence_row_action.jsp"
					/>
				</liferay-ui:search-container-row>
				<liferay-ui:search-iterator />
			</liferay-ui:search-container>

		</dd>
	</div>
</div>