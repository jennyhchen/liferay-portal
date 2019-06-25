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

package com.liferay.powwow.meetings.portlet;

import com.google.ical.iter.RecurrenceIterator;
import com.google.ical.iter.RecurrenceIteratorFactory;
import com.google.ical.values.DateValue;
import com.google.ical.values.DateValueImpl;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.PositionalWeekday;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowMeetingConstants;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.model.PowwowParticipantConstants;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.provider.PowwowServiceProvider;
import com.liferay.powwow.provider.PowwowServiceProviderUtil;
import com.liferay.powwow.provider.zoom.ZoomRecurrenceSerializer;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.PowwowMeetingOccurrenceLocalServiceUtil;
import com.liferay.powwow.service.PowwowMeetingOccurrenceServiceUtil;
import com.liferay.powwow.service.PowwowMeetingServiceUtil;
import com.liferay.powwow.service.PowwowParticipantLocalServiceUtil;
import com.liferay.powwow.util.PowwowSubscriptionSender;
import com.liferay.powwow.util.PowwowUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Shinn Lok
 * @author Marco Calderon
 * @author Evan Thibodeau
 */
public class MeetingsPortlet extends MVCPortlet {

	public void deletePowwowMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long powwowMeetingId = ParamUtil.getLong(
			actionRequest, "powwowMeetingId");

		try {
			if (PowwowServiceProviderUtil.isPowwowMeetingCreated(
					powwowMeetingId)) {

				if (PowwowServiceProviderUtil.isPowwowMeetingRunning(
						powwowMeetingId)) {

					PowwowServiceProviderUtil.endPowwowMeeting(powwowMeetingId);
				}

				PowwowServiceProviderUtil.deletePowwowMeeting(powwowMeetingId);
			}

			_deletePowwowMeetingOccurrence(powwowMeetingId);

			PowwowMeetingServiceUtil.deletePowwowMeeting(powwowMeetingId);

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			jsonObject.put(
				"message",
				translate(actionRequest, "the-meeting-could-not-be-deleted"));
			jsonObject.put("success", false);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void deleteOccurrence(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			PowwowMeeting.class.getName(), actionRequest);
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		long occurrenceId = ParamUtil.getLong(actionRequest, "occurrenceId", 0);

		serviceContext.setAttribute("sendNotification", Boolean.FALSE);

		try {
			if (occurrenceId > 0) {

				PowwowMeetingOccurrence powwowMeetingOccurrence =
					PowwowMeetingOccurrenceLocalServiceUtil
						.fetchPowwowMeetingOccurrence(occurrenceId);

				PowwowMeetingOccurrenceServiceUtil
					.updateOccurrenceStatus(
						powwowMeetingOccurrence.getPowwowMeetingId(),
						occurrenceId, OccurrenceStatus.DELETE);

				if (powwowMeetingOccurrence.getCalendarBookingId() > 0) {
					CalendarBookingLocalServiceUtil
						.updateStatus(
							themeDisplay.getUserId(), powwowMeetingOccurrence.getCalendarBookingId(),
							CalendarBookingWorkflowConstants.STATUS_INACTIVE, serviceContext);
				}
				else {
					PowwowMeeting powwowMeeting =
						PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(
							powwowMeetingOccurrence.getPowwowMeetingId());

					CalendarBooking mainCalendarBooking =
						CalendarBookingLocalServiceUtil.fetchCalendarBooking(
							powwowMeeting.getCalendarBookingId());

					_updateCalendarBookingExceptionDate(powwowMeetingOccurrence,
						mainCalendarBooking, themeDisplay, serviceContext);
				}

				PowwowServiceProviderUtil
					.deleteOccurrence(
						powwowMeetingOccurrence.getPowwowMeetingId(),
						powwowMeetingOccurrence.getOccurrenceApiId());
			}

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			jsonObject.put(
				"message",
				translate(actionRequest, "the-occurrence-could-not-be-deleted"));
			jsonObject.put("success", false);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void endPowwowMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long powwowMeetingId = ParamUtil.getLong(
			actionRequest, "powwowMeetingId");

		PowwowServiceProviderUtil.endPowwowMeeting(powwowMeetingId);
	}

	public void joinPowwowMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long powwowMeetingId = ParamUtil.getLong(
			actionRequest, "powwowMeetingId");
		long powwowParticipantId = ParamUtil.getLong(
			actionRequest, "powwowParticipantId");

		String hash = ParamUtil.getString(actionRequest, "hash");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		PowwowParticipant powwowParticipant =
			PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
				powwowParticipantId);

		if ((powwowMeetingId > 0) &&
			!hash.equals(PowwowUtil.getHash(powwowMeetingId))) {

			jsonObject.put("success", Boolean.FALSE);

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		try {
			PowwowMeeting powwowMeeting =
				PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

			String name = StringPool.BLANK;

			if (PowwowServiceProviderUtil.isSupportsPresettingParticipantName()) {

				name = ParamUtil.getString(actionRequest, "name");

				if ((powwowParticipant != null) &&
					!name.equals(powwowParticipant.getName())) {

					powwowParticipant.setName(name);

					PowwowParticipantLocalServiceUtil.updatePowwowParticipant(
						powwowParticipant);
				}
			}

			int type = PowwowParticipantConstants.TYPE_ATTENDEE;

			if (powwowParticipant != null) {
				type = powwowParticipant.getType();
			}

			if (powwowMeeting.getPowwowServerId() ==
					PowwowMeetingConstants.POWWOW_SERVER_ID_DEFAULT) {

				if (type == PowwowParticipantConstants.TYPE_ATTENDEE) {
					jsonObject.put("retry", Boolean.TRUE);

					writeJSON(actionRequest, actionResponse, jsonObject);

					return;
				}

				long powwowServerId =
					PowwowServiceProviderUtil.getPowwowServerId();

				Map<String, Serializable> providerTypeMetadataMap =
					PowwowServiceProviderUtil.addPowwowMeeting(
						powwowMeeting.getUserId(), powwowServerId,
						powwowMeetingId, powwowMeeting.getName(),
						new HashMap<String, String>());

				powwowMeeting.setPowwowServerId(powwowServerId);

				powwowMeeting.setProviderTypeMetadata(
					JSONFactoryUtil.serialize(providerTypeMetadataMap));

				PowwowMeetingLocalServiceUtil.updatePowwowMeeting(
					powwowMeeting);
			}

			if (!PowwowServiceProviderUtil.isPowwowMeetingRunning(
					powwowMeetingId)) {

				if (type == PowwowParticipantConstants.TYPE_ATTENDEE) {
					jsonObject.put("retry", Boolean.TRUE);

					writeJSON(actionRequest, actionResponse, jsonObject);

					return;
				}

				powwowMeeting.setStatus(
					PowwowMeetingConstants.STATUS_IN_PROGRESS);

				PowwowMeetingLocalServiceUtil.updatePowwowMeeting(
					powwowMeeting);
			}

			String joinPowwowMeetingURL =
				PowwowServiceProviderUtil.getJoinPowwowMeetingURL(
					powwowMeetingId, name, type);

			jsonObject.put("joinPowwowMeetingURL", joinPowwowMeetingURL);

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.putException(e);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String resourceID = resourceRequest.getResourceID();

		if (resourceID.equals("exportPowwowMeetingCalendar")) {
			exportPowwowMeetingCalendar(resourceRequest, resourceResponse);
		}
		else if (resourceID.equals("getEmailNotificationPreview")) {
			getEmailNotificationPreview(resourceRequest, resourceResponse);
		}
		else if (resourceID.equals("getUsers")) {
			getUsers(resourceRequest, resourceResponse);
		}
		else if (resourceID.equals("checkMaxOccurrence")) {
			checkMaxOccurrenceDate(resourceRequest, resourceResponse);
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	public void updatePowwowMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			_validateRecurrence(actionRequest);

			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

			long powwowMeetingId = ParamUtil.getLong(
				actionRequest, "powwowMeetingId");

			String name = ParamUtil.getString(actionRequest, "name");
			String description = ParamUtil.getString(actionRequest, "description");
			String languageId = ParamUtil.getString(actionRequest, "languageId");

			PowwowMeeting powwowMeeting = null;
			String oldRecurrenceHash = StringPool.BLANK;

			if (powwowMeetingId > 0) {
				powwowMeeting = PowwowMeetingServiceUtil.getPowwowMeeting(
					powwowMeetingId);

				CalendarBooking oldCalendarBooking =
					CalendarBookingLocalServiceUtil.fetchCalendarBooking(
						powwowMeeting.getCalendarBookingId());

				oldRecurrenceHash = _getRecurrenceHash(oldCalendarBooking);
			}

			List<PowwowParticipant> powwowParticipants =
				PowwowUtil.getPowwowParticipants(actionRequest);

			long hostUserId = getHostUserId(
				themeDisplay.getCompanyId(), powwowParticipants);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				PowwowMeeting.class.getName(), actionRequest);

			CalendarBooking calendarBooking = updateCalendarBooking(
				actionRequest, powwowMeeting, powwowParticipants, serviceContext);

			Map<String, String> options = new HashMap<>();

			boolean autoStartVideo = ParamUtil.getBoolean(
				actionRequest, "autoStartVideo");

			options.put(
				PowwowMeetingConstants.OPTION_AUTO_START_VIDEO,
				Boolean.toString(autoStartVideo));

			boolean requirePassword = ParamUtil.getBoolean(
				actionRequest, "requirePassword");

			String password = ParamUtil.getString(actionRequest, "password");

			if (requirePassword && !password.equals(StringPool.BLANK)) {
				options.put(PowwowMeetingConstants.OPTION_PASSWORD, password);
			}

			_addRecurrenceOptions(calendarBooking, options);

			Map<String, Serializable> providerTypeMetadataMap = new HashMap<>();

			if (powwowMeetingId <= 0) {
				long powwowServerId =
					PowwowMeetingConstants.POWWOW_SERVER_ID_DEFAULT;

				int addPowwowMeetingStrategy =
					PowwowServiceProviderUtil.getAddPowwowMeetingStrategy();

				if (addPowwowMeetingStrategy ==
						PowwowServiceProvider.ADD_POWWOW_MEETING_STRATEGY_EAGER) {

					powwowServerId = PowwowServiceProviderUtil.getPowwowServerId();

					providerTypeMetadataMap =
						PowwowServiceProviderUtil.addPowwowMeeting(
							hostUserId, powwowServerId, powwowMeetingId, name, options);
				}

				String portletId = PortalUtil.getPortletId(actionRequest);

				powwowMeeting = PowwowMeetingServiceUtil.addPowwowMeeting(
					themeDisplay.getScopeGroupId(), portletId, powwowServerId, name,
					description, providerTypeMetadataMap, languageId,
					calendarBooking.getCalendarBookingId(),
					PowwowMeetingConstants.STATUS_SCHEDULED, powwowParticipants,
					serviceContext);
			}
			else {
				int addPowwowMeetingStrategy =
					PowwowServiceProviderUtil.getAddPowwowMeetingStrategy();

				if (addPowwowMeetingStrategy ==
						PowwowServiceProvider.ADD_POWWOW_MEETING_STRATEGY_EAGER) {

					providerTypeMetadataMap =
						PowwowServiceProviderUtil.updatePowwowMeeting(
							powwowMeetingId, name, hostUserId, options);
				}

				powwowMeeting = PowwowMeetingServiceUtil.updatePowwowMeeting(
					powwowMeetingId, powwowMeeting.getPowwowServerId(), name,
					description, providerTypeMetadataMap, languageId,
					calendarBooking.getCalendarBookingId(),
					PowwowMeetingConstants.STATUS_SCHEDULED, powwowParticipants,
					serviceContext);
			}

			String newRecurrenceHash = _getRecurrenceHash(calendarBooking);

			boolean recurrenceChanged = !oldRecurrenceHash.equals(newRecurrenceHash);

			if (recurrenceChanged) {
				if (powwowMeetingId > 0) {
					_deletePowwowMeetingOccurrence(powwowMeetingId);
				}

				if (calendarBooking.isRecurring()) {
					addPowwowMeetingOccurrences(themeDisplay.getScopeGroupId(),
						powwowMeeting, calendarBooking);
				}
			}

			jsonObject.put("success", true);
		}
		catch (Exception e) {

			_log.error("Error while updating meeting.", e);

			jsonObject.put(
				"message",
				translate(actionRequest, e.getMessage()));
			jsonObject.put("success", false);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updatePowwowMeetingOccurrence(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			PowwowMeeting.class.getName(), actionRequest);
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long powwowMeetingId = ParamUtil.getLong(actionRequest, "powwowMeetingId");
		long occurrenceId = ParamUtil.getLong(actionRequest, "occurrenceId");

		try {
			PowwowMeetingOccurrence powwowMeetingOccurrence =
				PowwowMeetingOccurrenceLocalServiceUtil
					.fetchPowwowMeetingOccurrence(occurrenceId);

			long startTime = getTime(
				actionRequest, "startTime", getTimeZone(actionRequest));
			long endTime = getTime(
				actionRequest, "endTime", getTimeZone(actionRequest));

			_validateOccurrenceTime(startTime);

			boolean isUpdate =
				powwowMeetingOccurrence.getStartTime() != startTime ||
					powwowMeetingOccurrence.getEndTime() != endTime;

			if (isUpdate) {

				PowwowMeeting powwowMeeting =
					PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(powwowMeetingId);

				CalendarBooking calendarBooking =
					CalendarBookingLocalServiceUtil
						.fetchCalendarBooking(powwowMeeting.getCalendarBookingId());

				if (Validator.isNotNull(calendarBooking)) {
					updateOccurrence(powwowMeeting, calendarBooking, powwowMeetingOccurrence,
						startTime, endTime, themeDisplay, serviceContext);

				}
			}
		}
		catch (PortalException e) {
			_log.error(e);
			SessionErrors.add(actionRequest, e.getMessage());
		}
		catch (Exception e) {
			_log.error(e);
			SessionErrors.add(actionRequest, "error-while-updating-meeting-occurrence");
		}

		sendRedirect(actionRequest, actionResponse);
	}

	protected void addPowwowMeetingOccurrences(
			long groupId, PowwowMeeting powwowMeeting, CalendarBooking calendarBooking)
		throws Exception {

		long powwowMeetingId = powwowMeeting.getPowwowMeetingId();

		JSONObject meetingJSONObject =
			PowwowServiceProviderUtil.getMeetingJSONObject(powwowMeetingId);

		JSONArray occurrencesJsonArray =
			meetingJSONObject.getJSONArray("occurrences");

		for (int i = 0; i < occurrencesJsonArray.length(); i++) {
			JSONObject occurrenceJSONObject =
				occurrencesJsonArray.getJSONObject(i);

			TimeZone calendarBookingTimeZone = calendarBooking.getTimeZone();

			_addPowwowMeetingOccurrence(occurrenceJSONObject, groupId, powwowMeetingId,
				calendarBookingTimeZone);
		}
	}

	protected void checkMaxOccurrenceDate(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
			boolean isValid = true;

			TimeZone timeZone = getTimeZone(resourceRequest);
			Recurrence recurrence = getRecurrence(resourceRequest);

			Calendar startTimeCalendar =
				getJCalendar(resourceRequest, "startTime", timeZone);
			Calendar untilJCalendar =
				getJCalendar(resourceRequest, "untilDate", timeZone);

			Calendar limitDateJCalendar =
				_findLimitDate(recurrence, startTimeCalendar);

			boolean isUntilDateExceededLimitDate =
				Validator.isNotNull(limitDateJCalendar) &&
				Validator.isNotNull(untilJCalendar) &&
					untilJCalendar.after(limitDateJCalendar);

			if (isUntilDateExceededLimitDate) {

				DateFormat dateFormat = DateFormatFactoryUtil.getDate(resourceRequest.getLocale());

				String maxDateFormatted = dateFormat.format(limitDateJCalendar.getTime());

				String errorMessage = LanguageUtil.format(
					resourceRequest.getLocale(),
					"occurrences-exceeded-50-times-please-choose-a-date-that-not-after-x",
					maxDateFormatted);

				jsonResponse.put("errorMessage", errorMessage);

				isValid = false;
			}

			jsonResponse.put("valid", isValid);

			writeJSON(resourceRequest, resourceResponse, jsonResponse);
		}
		catch (Exception e) {
			_log.error("Error while get max occurrence date", e);

			throw new IOError(e);
		}
	}

	protected void exportPowwowMeetingCalendar(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		Long powwowMeetingId = ParamUtil.getLong(
			resourceRequest, "powwowMeetingId");

		try {
			ByteArrayOutputStream byteArrayOutputStream =
				new ByteArrayOutputStream();

			byteArrayOutputStream.write(
				PowwowUtil.exportPowwowMeetingCalendar(powwowMeetingId));

			resourceResponse.setContentLength(byteArrayOutputStream.size());

			resourceResponse.setContentType(ContentTypes.TEXT_CALENDAR);

			String contentDispositionHeader =
				HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT +
					"; filename=invite.ics";

			resourceResponse.addProperty(
				HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeader);

			OutputStream outputStream =
				resourceResponse.getPortletOutputStream();

			byteArrayOutputStream.writeTo(outputStream);

			outputStream.flush();

			outputStream.close();
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected String getCalendarBookingDescription(
			ActionRequest actionRequest, PowwowMeeting powwowMeeting,
			ServiceContext serviceContext)
		throws Exception {

		if (powwowMeeting == null) {
			return StringPool.BLANK;
		}

		String description = ParamUtil.getString(actionRequest, "description");

		String invitationURLMarkup = getInvitationURLMarkup(
			powwowMeeting.getPowwowMeetingId(), serviceContext);

		return invitationURLMarkup + description;
	}

	protected long getCalendarId(long userId, ServiceContext serviceContext)
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(User.class);

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.fetchCalendarResource(
				classNameId, userId);

		if (calendarResource == null) {
			User user = UserLocalServiceUtil.getUser(userId);

			Group userGroup = null;

			String userName = user.getFullName();

			if (user.isDefaultUser()) {
				userGroup = GroupLocalServiceUtil.getGroup(
					serviceContext.getCompanyId(), GroupConstants.GUEST);

				userName = GroupConstants.GUEST;
			}
			else {
				userGroup = GroupLocalServiceUtil.getUserGroup(
					serviceContext.getCompanyId(), userId);
			}

			Map<Locale, String> nameMap = new HashMap<>();

			nameMap.put(LocaleUtil.getDefault(), userName);

			Map<Locale, String> descriptionMap = new HashMap<>();

			calendarResource =
				CalendarResourceLocalServiceUtil.addCalendarResource(
					userId, userGroup.getGroupId(),
					PortalUtil.getClassNameId(User.class), userId, null, null,
					nameMap, descriptionMap, true, serviceContext);

			if (calendarResource.getDefaultCalendarId() <= 0) {
				CalendarLocalServiceUtil.addCalendar(
					userId, calendarResource.getGroupId(),
					calendarResource.getCalendarResourceId(),
					calendarResource.getNameMap(),
					calendarResource.getDescriptionMap(),
					calendarResource.getTimeZoneId(), 0, true, false, false,
					serviceContext);
			}
		}

		return calendarResource.getDefaultCalendarId();
	}

	protected long[] getChildCalendarIds(
			long companyId, List<PowwowParticipant> powwowParticipants,
			ServiceContext serviceContext)
		throws Exception {

		List<Long> childCalendarIds = new ArrayList<>();

		for (PowwowParticipant powwowParticipant : powwowParticipants) {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, powwowParticipant.getEmailAddress());

			if (user == null) {
				continue;
			}

			long calendarId = getCalendarId(user.getUserId(), serviceContext);

			if (calendarId > 0) {
				childCalendarIds.add(calendarId);
			}
		}

		return ArrayUtil.toArray(childCalendarIds.toArray(new Long[0]));
	}

	protected void getEmailNotificationPreview(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			Long powwowMeetingId = ParamUtil.getLong(
				resourceRequest, "powwowMeetingId");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				PowwowMeeting.class.getName(), resourceRequest);

			PowwowSubscriptionSender powwowSubscriptionSender =
				PowwowUtil.getPowwowSubscriptionSender(
					powwowMeetingId, serviceContext);

			powwowSubscriptionSender.initialize();

			jsonObject.put(
				"emailBody",
				powwowSubscriptionSender.getEmailNotificationBody(
					serviceContext.getLocale()));
			jsonObject.put(
				"emailSubject",
				powwowSubscriptionSender.getEmailNotificationSubject(
					serviceContext.getLocale()));

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put(
				"message",
				translate(
					resourceRequest, "unable-to-render-meeting-invitation"));
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected long getHostUserId(
			long companyId, List<PowwowParticipant> powwowParticipants)
		throws PortalException {

		for (PowwowParticipant powwowParticipant : powwowParticipants) {
			if (powwowParticipant.getType() ==
					PowwowParticipantConstants.TYPE_HOST) {

				return UserLocalServiceUtil.getUserIdByEmailAddress(
					companyId, powwowParticipant.getEmailAddress());
			}
		}

		return 0;
	}

	protected String getInvitationURLMarkup(
			long powwowMeetingId, ServiceContext serviceContext)
		throws Exception {

		String meetingURL = PowwowUtil.getInvitationURL(
			powwowMeetingId, null, serviceContext.getRequest());

		StringBundler sb = new StringBundler(5);

		sb.append("<a href=\"");
		sb.append(meetingURL);
		sb.append("\" target=\"_blank\">");
		sb.append(LanguageUtil.get(serviceContext.getLocale(), "join-meeting"));
		sb.append("</a><br />");

		return sb.toString();
	}

	protected Calendar getJCalendar(PortletRequest portletRequest, String name, TimeZone timeZone) {

		int month = ParamUtil.getInteger(portletRequest, name + "Month");
		int day = ParamUtil.getInteger(portletRequest, name + "Day");
		int year = ParamUtil.getInteger(portletRequest, name + "Year");
		int hour = ParamUtil.getInteger(portletRequest, name + "Hour");
		int minute = ParamUtil.getInteger(portletRequest, name + "Minute");

		int amPm = ParamUtil.getInteger(portletRequest, name + "AmPm");

		if (amPm == Calendar.PM) {
			hour += 12;
		}

		return JCalendarUtil.getJCalendar(year, month, day, hour, minute, 0, 0, timeZone);
	}

	protected Map<Locale, String> getLocalizationMap(String key) {
		Set<Locale> locales = LanguageUtil.getAvailableLocales();

		Map<Locale, String> map = new HashMap<>();

		for (Locale locale : locales) {
			map.put(locale, key);
		}

		return map;
	}

	protected Recurrence getRecurrence(PortletRequest portletRequest) {
		boolean repeat = ParamUtil.getBoolean(portletRequest, "repeat");

		if (!repeat) {
			return null;
		}

		Recurrence recurrence = new Recurrence();

		int count = 0;

		String ends = ParamUtil.getString(portletRequest, "ends");

		if (ends.equals("after")) {
			count = ParamUtil.getInteger(portletRequest, "count");
		}

		recurrence.setCount(count);

		Frequency frequency = Frequency.parse(
			ParamUtil.getString(portletRequest, "frequency"));

		recurrence.setFrequency(frequency);

		int interval = ParamUtil.getInteger(portletRequest, "interval");

		recurrence.setInterval(interval);

		TimeZone timeZone = getTimeZone(portletRequest);

		recurrence.setTimeZone(timeZone);

		Calendar startTimeJCalendar = getJCalendar(
			portletRequest, "startTime", timeZone);

		if (ends.equals("on")) {
			Calendar untilJCalendar = getJCalendar(
				portletRequest, "untilDate", timeZone);

			untilJCalendar = JCalendarUtil.mergeJCalendar(
				untilJCalendar, startTimeJCalendar, timeZone);

			recurrence.setUntilJCalendar(untilJCalendar);
		}

		List<PositionalWeekday> positionalWeekdays = new ArrayList<>();

		if (frequency == Frequency.WEEKLY) {
			String[] weekdayValues = ParamUtil.getStringValues(
				portletRequest, "weekdays");

			String weekdaysCheckbox =
				ParamUtil.getString(portletRequest, "weekdaysCheckbox");

			if (weekdayValues.length == 1 && weekdayValues[0].equals("false")) {
				weekdayValues = new String[] {
					weekdaysCheckbox
				};
			}
			else {
				weekdayValues = ArrayUtil.append(weekdayValues, new String[] {
					weekdaysCheckbox
				});
			}

			for (String weekdayValue : weekdayValues) {
				Weekday weekday = Weekday.parse(weekdayValue);

				Calendar weekdayJCalendar =
					JCalendarUtil.getJCalendar(
						startTimeJCalendar.getTimeInMillis(), timeZone);

				weekdayJCalendar.set(
					Calendar.DAY_OF_WEEK,
					weekday.getCalendarWeekday());

				weekday = Weekday.getWeekday(weekdayJCalendar);

				positionalWeekdays.add(new PositionalWeekday(weekday, 0));
			}
		}
		else if ((frequency == Frequency.MONTHLY) ||
			(frequency == Frequency.YEARLY)) {

			boolean repeatOnWeekday = ParamUtil.getBoolean(
				portletRequest, "repeatOnWeekday");

			if (repeatOnWeekday) {
				int position = ParamUtil.getInteger(portletRequest, "position");

				Weekday weekday = Weekday.parse(
					ParamUtil.getString(portletRequest, "weekday"));

				positionalWeekdays.add(
					new PositionalWeekday(weekday, position));

				if (frequency == Frequency.YEARLY) {
					List<Integer> months = Arrays.asList(
						ParamUtil.getInteger(portletRequest, "startTimeMonth"));

					recurrence.setMonths(months);
				}
			}
		}

		recurrence.setPositionalWeekdays(positionalWeekdays);

		String[] exceptionDates = StringUtil.split(
			ParamUtil.getString(portletRequest, "exceptionDates"));

		for (String exceptionDate : exceptionDates) {
			recurrence.addExceptionDate(
				JCalendarUtil.getJCalendar(Long.valueOf(exceptionDate)));
		}

		return recurrence;
	}

	protected long getTime(
		PortletRequest portletRequest, String name, TimeZone timeZone) {

		return getJCalendar(portletRequest, name, timeZone).getTimeInMillis();
	}

	protected TimeZone getTimeZone(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String timeZoneId = user.getTimeZoneId();

		return TimeZone.getTimeZone(timeZoneId);
	}

	protected void getUsers(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			String userName = ParamUtil.getString(resourceRequest, "name");

			List<User> users = UserLocalServiceUtil.search(
				themeDisplay.getCompanyId(), userName,
				WorkflowConstants.STATUS_ANY, null, 0, 10,
				(OrderByComparator)null);

			for (User user : users) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("emailAddress", user.getEmailAddress());
				jsonObject.put("fullName", user.getFullName());
				jsonObject.put(
					"portraitURL", user.getPortraitURL(themeDisplay));
				jsonObject.put("userId", user.getUserId());

				jsonArray.put(jsonObject);
			}

			writeJSON(resourceRequest, resourceResponse, jsonArray);
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected CalendarBooking updateCalendarBooking(
			ActionRequest actionRequest, PowwowMeeting powwowMeeting,
			List<PowwowParticipant> powwowParticipants,
			ServiceContext serviceContext)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CalendarBooking calendarBooking = null;

		if (powwowMeeting != null) {
			calendarBooking =
				CalendarBookingLocalServiceUtil.fetchCalendarBooking(
					powwowMeeting.getCalendarBookingId());
		}

		long calendarId = getCalendarId(
			themeDisplay.getUserId(), serviceContext);
		long[] childCalendarIds = getChildCalendarIds(
			themeDisplay.getCompanyId(), powwowParticipants, serviceContext);
		Map<Locale, String> titleMap = getLocalizationMap(
			ParamUtil.getString(actionRequest, "name"));
		Map<Locale, String> descriptionMap = getLocalizationMap(
			getCalendarBookingDescription(
				actionRequest, powwowMeeting, serviceContext));

		long startTime = getTime(
			actionRequest, "startTime", getTimeZone(actionRequest));
		long endTime = getTime(
			actionRequest, "endTime", getTimeZone(actionRequest));

		Recurrence recurrence = getRecurrence(actionRequest);
		String recurrenceSerializedData = RecurrenceSerializer.serialize(recurrence);

		serviceContext.setAttribute("sendNotification", Boolean.FALSE);

		if (calendarBooking != null) {
			if (calendarBooking.isInTrash()) {
				CalendarBookingLocalServiceUtil.restoreCalendarBookingFromTrash(
					themeDisplay.getUserId(),
					calendarBooking.getCalendarBookingId());
			}

			calendarBooking =
				CalendarBookingLocalServiceUtil.updateCalendarBooking(
					themeDisplay.getUserId(),
					calendarBooking.getCalendarBookingId(),
					calendarBooking.getCalendarId(), childCalendarIds, titleMap,
					descriptionMap, StringPool.BLANK, startTime, endTime, false,
					recurrenceSerializedData, 0, "email", 0, "email", serviceContext);
		}
		else {
			calendarBooking =
				CalendarBookingLocalServiceUtil.addCalendarBooking(
					themeDisplay.getUserId(), calendarId, childCalendarIds,
					CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
					titleMap, descriptionMap, StringPool.BLANK, startTime,
					endTime, false, recurrenceSerializedData, 0, "email", 0, "email",
					serviceContext);
		}

		return calendarBooking;
	}

	protected void updateOccurrence(
			PowwowMeeting powwowMeeting, CalendarBooking mainCalendarBooking,
			PowwowMeetingOccurrence powwowMeetingOccurrence, long startTime,
			long endTime, ThemeDisplay themeDisplay, ServiceContext serviceContext)
		throws Exception {

		serviceContext.setAttribute("sendNotification", Boolean.FALSE);

		_updateCalendarBookingExceptionDate(powwowMeetingOccurrence,
			mainCalendarBooking, themeDisplay, serviceContext);

		_updateCalendarBookingOccurrence(powwowMeeting, powwowMeetingOccurrence,
			startTime, endTime, mainCalendarBooking, themeDisplay, serviceContext);

		_updateOccurenceZoomApi(powwowMeeting, mainCalendarBooking,
			powwowMeetingOccurrence, startTime, endTime);

	}

	private void _addPowwowMeetingOccurrence(
			JSONObject zoomOccurrenceJSONObject, long groupId,
			long powwowMeetingId, TimeZone calendarBookingTimeZone)
		throws Exception {

		String occurrenceApiId = zoomOccurrenceJSONObject.getString("occurrence_id");
		OccurrenceStatus occurrenceStatus = OccurrenceStatus.parse(zoomOccurrenceJSONObject.getString("status"));
		String zoomOriginalData = zoomOccurrenceJSONObject.toString();
		String startTimeJSONString = zoomOccurrenceJSONObject.getString("start_time");
		int duration = zoomOccurrenceJSONObject.getInt("duration", 0);

		Calendar calendar =
			_parseZoomUtcTime(startTimeJSONString, calendarBookingTimeZone);

		long startTime = calendar.getTimeInMillis();

		calendar.add(Calendar.MINUTE, duration);

		long endTime = calendar.getTimeInMillis();

		PowwowMeetingOccurrenceServiceUtil.addPowwowMeetingOccurrence(
			groupId, occurrenceApiId, powwowMeetingId, occurrenceStatus,
			zoomOriginalData, startTime, endTime);
	}

	private void _addRecurrenceOptions(
		CalendarBooking calendarBooking, Map<String, String> options) {

		Recurrence recurrence = calendarBooking.getRecurrenceObj();

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			calendarBooking.getStartTime(), calendarBooking.getTimeZone());

		String recurrenceJson = ZoomRecurrenceSerializer
			.toJSONString(recurrence, startTimeJCalendar);

		if (!Validator.isBlank(recurrenceJson)) {
			options.put(PowwowMeetingConstants.OPTION_RECURRENCE,
				recurrenceJson);

			String zoomStartTimeUTC =
				PowwowServiceProviderUtil
					.toZoomDateTimeUTC(startTimeJCalendar);
			options.put(PowwowMeetingConstants.OPTION_START_TIME,
				zoomStartTimeUTC);

			long minutes =
				_getDurationInMinutes(
					calendarBooking.getStartTime(), calendarBooking.getEndTime());

			options.put(PowwowMeetingConstants.OPTION_DURATION,
				String.valueOf(minutes));
		}
	}

	private void _deletePowwowMeetingOccurrence(long powwowMeetingId)
		throws PortalException {
		PowwowMeetingOccurrenceServiceUtil.deleteByPowwowMeetingId(powwowMeetingId);
	}

	private boolean _exceptionDateExisted(
		final Recurrence recurrence, final Calendar startTimeOriginalCalendar) {

		List<Calendar> exceptions = recurrence.getExceptionJCalendars();

		if (Validator.isNull(exceptions) || exceptions.isEmpty()) {
			return false;
		}

		int year = startTimeOriginalCalendar.get(Calendar.YEAR);
		int month = startTimeOriginalCalendar.get(Calendar.MONTH);
		int day = startTimeOriginalCalendar.get(Calendar.DATE);
		TimeZone timeZone = startTimeOriginalCalendar.getTimeZone();

		Calendar startDateCalendar = CalendarFactoryUtil.getCalendar(year,
			month, day, 0, 0, 0, 0, timeZone);

		return exceptions.contains(startDateCalendar);
	}

	private Calendar _findLimitDate(
		Recurrence recurrence, Calendar startTimeCalendar)
		throws ParseException {

		if (recurrence.getCount() > 0 ||
			Validator.isNull(recurrence.getUntilJCalendar())) {

			// Only find limit date if using untilDate

			return null;
		}

		String rRule = RecurrenceSerializer.serialize(recurrence);

		DateValue startDateValue = _toDateValue(startTimeCalendar);

		TimeZone timeZone = startTimeCalendar.getTimeZone();

		RecurrenceIterator recurrenceIterator =
			RecurrenceIteratorFactory.createRecurrenceIterator(
				rRule, startDateValue, timeZone);

		DateValue maxDateOccurrence = null;

		for (int i = 1; i <= 50 && recurrenceIterator.hasNext(); i++) {

			if (i == 50) {
				maxDateOccurrence = recurrenceIterator.next();
				break;
			}

			recurrenceIterator.next();
		}

		return Validator.isNotNull(maxDateOccurrence) ? _toJCalendar(maxDateOccurrence, timeZone) : null;
	}

	private long _getDurationInMinutes(long startTime, long endTime) {

		Duration duration = Duration.ofMillis(Math.abs(endTime - startTime));
		return duration.abs().toMinutes();
	}

	private String _getRecurrenceHash(CalendarBooking calendarBooking) {

		if (Validator.isNull(calendarBooking) ||
			!calendarBooking.isRecurring()) {
			return StringPool.BLANK;
		}

		Recurrence recurrence = calendarBooking.getRecurrenceObj();

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			calendarBooking.getStartTime(), calendarBooking.getTimeZone());

		String recurrenceJson = ZoomRecurrenceSerializer
			.toJSONString(recurrence, startTimeJCalendar);

		return DigesterUtil.digestHex(Digester.SHA_1, recurrenceJson,
			String.valueOf(calendarBooking.getStartTime()),
			String.valueOf(calendarBooking.getEndTime()));
	}

	private Calendar _getUtcCalendar() {

		if (Validator.isNotNull(_utcCalendar)) {
			return _utcCalendar;
		}

		_utcCalendar = CalendarFactoryUtil.getCalendar(_getUtcTimeZone());
		return _utcCalendar;
	}

	private TimeZone _getUtcTimeZone() {

		if (Validator.isNotNull(_utcTimeZone)) {
			return _utcTimeZone;
		}

		_utcTimeZone = TimeZone.getTimeZone(StringPool.UTC);

		return _utcTimeZone;
	}

	private DateFormat _getZoomUtcDateFormat() {

		if (Validator.isNotNull(_zoomUtcDateFormat)) {
			return _zoomUtcDateFormat;
		}

		_zoomUtcDateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			PowwowServiceProviderUtil.ZOOM_UTC_DATETIME_PATTERN,
			_getUtcTimeZone());

		return _zoomUtcDateFormat;
	}

	private Calendar _parseZoomUtcTime(
			String zoomUtcTimeString, TimeZone timeZone)
		throws Exception {

		Calendar utcCalendar = _getUtcCalendar();

		DateFormat zoomUtcDateFormat = _getZoomUtcDateFormat();

		utcCalendar.setTime(zoomUtcDateFormat.parse(zoomUtcTimeString));

		return JCalendarUtil.getJCalendar(utcCalendar, timeZone);
	}

	private void _validateOccurrenceTime(long startTime)
		throws PortalException {

		Calendar startTimeCalendar = CalendarFactoryUtil.getCalendar(startTime);

		Calendar currentTimeCalendar = Calendar.getInstance();

		if (startTimeCalendar.before(currentTimeCalendar)) {
			throw new PortalException("start-time-must-be-a-future-time");
		}
	}

	private void _validateRecurrence(ActionRequest actionRequest)
		throws PortalException {

		Recurrence recurrence = getRecurrence(actionRequest);

		if (Validator.isNull(recurrence)) {
			return;
		}

		if (Frequency.YEARLY.equals(recurrence.getFrequency())) {
			throw new PortalException("invalid-frequency");
		}

		boolean isValidDailyInterval =
			Frequency.DAILY.equals(recurrence.getFrequency()) &&
				recurrence.getInterval() <= 30;

		boolean isValidWeeklyInterval =
			Frequency.WEEKLY.equals(recurrence.getFrequency()) &&
				recurrence.getInterval() <= 12;

		boolean isValidMonthlyInterval =
			Frequency.MONTHLY.equals(recurrence.getFrequency()) &&
				recurrence.getInterval() <= 3;

		if (!(isValidDailyInterval || isValidWeeklyInterval ||
			isValidMonthlyInterval)) {
			throw new PortalException("invalid-interval");
		}

		boolean hasStopRepeating =
			Validator.isNotNull(recurrence.getUntilJCalendar()) ||
				recurrence.getCount() > 0;

		if (!hasStopRepeating) {
			throw new PortalException("recurrence-must-have-stop-repeating-choice");
		}

		boolean isValidNumberOfOccurrence =
			(Validator.isNotNull(recurrence.getUntilJCalendar()) &&
				recurrence.getCount() <= 0) ||
				(recurrence.getCount() >= 2 && recurrence.getCount() <= 50);

		if (!isValidNumberOfOccurrence) {
			throw new PortalException("invalid-number-of-occurrence");
		}

		if (Validator.isNotNull(recurrence.getUntilJCalendar()) &&
			recurrence.getCount() <= 0) {

			TimeZone timeZone = getTimeZone(actionRequest);

			Calendar startTimeCalendar =
				getJCalendar(actionRequest, "startTime", timeZone);
			Calendar untilJCalendar =
				getJCalendar(actionRequest, "untilDate", timeZone);

			try {
				Calendar limitDateJCalendar = _findLimitDate(recurrence, startTimeCalendar);

				boolean isUntilDateExceededLimitDate =
					Validator.isNotNull(limitDateJCalendar) &&
						Validator.isNotNull(untilJCalendar) &&
						untilJCalendar.after(limitDateJCalendar);

				if (isUntilDateExceededLimitDate) {
					throw new PortalException(
						"occurrences-exceeded-50-times-please-choose-an-earlier-date");
				}
			}
			catch (ParseException e) {
				_log.warn("Error while parsing recurrence rule.", e);
			}
		}
	}

	private DateValue _toDateValue(Calendar calendar) {

		return new DateValueImpl(calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH) + 1,
			calendar.get(Calendar.DAY_OF_MONTH));
	}

	private Calendar _toJCalendar(DateValue dateValue, TimeZone timeZone) {

		return JCalendarUtil.getJCalendar(dateValue.year(),
			dateValue.month() - 1, dateValue.day(), 23, 59, 59, 990, timeZone);
	}

	private void _updateCalendarBookingExceptionDate(
			PowwowMeetingOccurrence powwowMeetingOccurrence,
			CalendarBooking calendarBooking, ThemeDisplay themeDisplay,
			ServiceContext serviceContext)
		throws Exception {

		Recurrence recurrence = calendarBooking.getRecurrenceObj();

		String zoomOriginalData = powwowMeetingOccurrence.getZoomOriginalData();
		JSONObject zoomOriginalDataJSON =
			JSONFactoryUtil.createJSONObject(zoomOriginalData);

		Calendar startTimeOriginalCalendar =
			_parseZoomUtcTime(zoomOriginalDataJSON.getString("start_time"),
				calendarBooking.getTimeZone());

		if (_exceptionDateExisted(recurrence, startTimeOriginalCalendar)) {
			return;
		}

		recurrence.addExceptionDate(startTimeOriginalCalendar);

		String recurrenceSerializedData =
			RecurrenceSerializer.serialize(recurrence);

		CalendarBookingLocalServiceUtil.updateCalendarBooking(
			themeDisplay.getUserId(), calendarBooking.getCalendarBookingId(),
			calendarBooking.getCalendarId(), calendarBooking.getTitleMap(),
			new HashMap<>(), StringPool.BLANK,
			calendarBooking.getStartTime(), calendarBooking.getEndTime(), false,
			recurrenceSerializedData, 0, "email", 0, "email", serviceContext);
	}

	private void _updateCalendarBookingOccurrence(
			PowwowMeeting powwowMeeting,
			PowwowMeetingOccurrence powwowMeetingOccurrence, long startTime,
			long endTime, CalendarBooking mainCalendarBooking,
			ThemeDisplay themeDisplay, ServiceContext serviceContext)
		throws Exception {

		long calendarBookingId = 0;
		long occurrenceId = powwowMeetingOccurrence.getOccurrenceId();
		long powwowMeetingId = powwowMeeting.getPowwowMeetingId();

		if (powwowMeetingOccurrence.getCalendarBookingId() > 0) {
			CalendarBooking occurrenceCalendarBooking =
				CalendarBookingLocalServiceUtil.fetchCalendarBooking(
					powwowMeetingOccurrence.getCalendarBookingId());

			calendarBookingId = occurrenceCalendarBooking.getCalendarBookingId();

			CalendarBookingLocalServiceUtil.updateCalendarBooking(
				themeDisplay.getUserId(), calendarBookingId,
				occurrenceCalendarBooking.getCalendarId(),
				mainCalendarBooking.getTitleMap(),
				new HashMap<>(), StringPool.BLANK,
				startTime, endTime,
				false, StringPool.BLANK, 0, "email", 0, "email",
				serviceContext);
		}
		else {
			long calendarId =
				getCalendarId(themeDisplay.getUserId(), serviceContext);

			long[] childCalendarIds = CalendarBookingLocalServiceUtil
				.getChildCalendarIds(mainCalendarBooking.getCalendarBookingId(),
					mainCalendarBooking.getCalendarId());

			CalendarBooking occurrenceCalendarBooking =
				CalendarBookingLocalServiceUtil.addCalendarBooking(
					themeDisplay.getUserId(), calendarId, childCalendarIds,
					CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
					mainCalendarBooking.getTitleMap(),
					mainCalendarBooking.getDescriptionMap(), StringPool.BLANK,
					startTime, endTime,
					false, StringPool.BLANK, 0, "email", 0, "email",
					serviceContext);

			calendarBookingId = occurrenceCalendarBooking.getCalendarBookingId();
		}

		PowwowMeetingOccurrenceServiceUtil.updateOccurrenceTime(powwowMeetingId,
			occurrenceId, startTime, endTime, calendarBookingId);
	}

	private void _updateOccurenceZoomApi(
			PowwowMeeting powwowMeeting, CalendarBooking calendarBooking,
			PowwowMeetingOccurrence powwowMeetingOccurrence, long startTime,
			long endTime)
		throws PortalException {

		Map<String, String> options = new HashMap<>();
		String occurrenceApiId = powwowMeetingOccurrence.getOccurrenceApiId();

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime,
			calendarBooking.getTimeZone());

		String zoomStartTimeUTC =
			PowwowServiceProviderUtil.toZoomDateTimeUTC(startTimeJCalendar);
		options.put(PowwowMeetingConstants.OPTION_START_TIME, zoomStartTimeUTC);

		long duration = _getDurationInMinutes(startTime, endTime);

		options.put(PowwowMeetingConstants.OPTION_DURATION,
			String.valueOf(duration));

		PowwowServiceProviderUtil.updateOccurrence(
			powwowMeeting.getPowwowMeetingId(), options, occurrenceApiId);
	}

	private Calendar _utcCalendar;
	private TimeZone _utcTimeZone;
	private DateFormat _zoomUtcDateFormat;

	private static final Log _log = LogFactoryUtil.getLog(MeetingsPortlet.class);
}
