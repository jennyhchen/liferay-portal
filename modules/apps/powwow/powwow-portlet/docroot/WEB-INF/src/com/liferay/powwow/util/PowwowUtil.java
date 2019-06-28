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

package com.liferay.powwow.util;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.PositionalWeekday;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.powwow.meetings.portlet.MeetingsPortlet;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.model.PowwowParticipantConstants;
import com.liferay.powwow.provider.PowwowServiceProviderUtil;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.PowwowParticipantLocalServiceUtil;

import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

/**
 * @author Shinn Lok
 */
public class PowwowUtil {

	public static byte[] exportPowwowMeetingCalendar(long powwowMeetingId)
		throws Exception {

		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		String calendarBookingString =
			CalendarBookingLocalServiceUtil.exportCalendarBooking(
				powwowMeeting.getCalendarBookingId(),
				CalendarUtil.ICAL_EXTENSION);

		return calendarBookingString.getBytes();
	}

	public static String getHash(long powwowMeetingId) throws Exception {
		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		return DigesterUtil.digestHex(
			Digester.SHA_1, String.valueOf(powwowMeetingId),
			String.valueOf(powwowMeeting.getUserId()),
			String.valueOf(powwowMeeting.getUserUuid()));
	}

	public static String getInvitationURL(
			long powwowMeetingId, PowwowParticipant powwowParticipant,
			HttpServletRequest request)
		throws Exception {

		StringBundler sb = new StringBundler(9);

		Layout layout = null;

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = GroupLocalServiceUtil.fetchGroup(
			themeDisplay.getCompanyId(),
			PortletPropsValues.POWWOW_INVITATION_GROUP_NAME);

		if (group != null) {
			layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
				group.getGroupId(),
				PortletPropsValues.POWWOW_INVITATION_LAYOUT_PRIVATE,
				PortletPropsValues.POWWOW_INVITATION_LAYOUT_FRIENDLY_URL);
		}

		if (layout == null) {
			group = GroupLocalServiceUtil.getGroup(
				themeDisplay.getCompanyId(), GroupConstants.GUEST);

			layout = LayoutLocalServiceUtil.getLayout(
				group.getDefaultPublicPlid());
		}

		sb.append(PortalUtil.getLayoutURL(layout, themeDisplay));

		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("meetings");
		sb.append(StringPool.SLASH);
		sb.append(powwowMeetingId);
		sb.append(StringPool.SLASH);

		long powwowParticipantId = 0;

		if (powwowParticipant != null) {
			powwowParticipantId = powwowParticipant.getPowwowParticipantId();
		}

		sb.append(powwowParticipantId);

		sb.append(StringPool.SLASH);

		String hash = null;

		if (powwowMeetingId > 0) {
			hash = getHash(powwowMeetingId);
		}

		sb.append(hash);

		return sb.toString();
	}

	public static Sort[] getPowwowMeetingSorts(
		String orderByCol, String orderByType) {

		if (Validator.isNull(orderByCol) || Validator.isNull(orderByType)) {
			return SortFactoryUtil.getDefaultSorts();
		}

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		if (orderByCol.equals("created-by")) {
			return new Sort[] {
				SortFactoryUtil.create("creatorName", Sort.STRING_TYPE, reverse)
			};
		}
		else if (orderByCol.equals("date")) {
			return new Sort[] {
				SortFactoryUtil.create("startTime", Sort.LONG_TYPE, reverse)
			};
		}
		else if (orderByCol.equals("name")) {
			return new Sort[] {
				SortFactoryUtil.create(Field.NAME, Sort.STRING_TYPE, reverse)
			};
		}

		return SortFactoryUtil.getDefaultSorts();
	}

	public static List<PowwowParticipant> getPowwowParticipants(
			ActionRequest actionRequest)
		throws Exception {

		List<PowwowParticipant> powwowParticipants = new ArrayList<>();

		long powwowMeetingId = ParamUtil.getLong(
			actionRequest, "powwowMeetingId");

		String participantsJSON = ParamUtil.getString(
			actionRequest, "participantsJSON");

		JSONArray participantsJSONArray = JSONFactoryUtil.createJSONArray(
			participantsJSON);

		for (int i = 0; i < participantsJSONArray.length(); i++) {
			JSONObject participantJSONObject =
				participantsJSONArray.getJSONObject(i);

			String name = participantJSONObject.getString("name");
			long participantUserId = participantJSONObject.getLong(
				"participantUserId");
			String emailAddress = participantJSONObject.getString(
				"emailAddress");
			int type = participantJSONObject.getInt("type");

			if (Validator.isNull(name) && Validator.isNull(emailAddress)) {
				continue;
			}

			PowwowParticipant powwowParticipant = _getPowwowParticipant(
				powwowMeetingId, name, participantUserId, emailAddress, type);

			powwowParticipants.add(powwowParticipant);
		}

		return powwowParticipants;
	}

	public static PowwowSubscriptionSender getPowwowSubscriptionSender(
			long powwowMeetingId, long calendarBookingId,
			ServiceContext serviceContext)
		throws Exception {

		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		return _getSubcriptionSender(powwowMeeting, calendarBookingId,
			serviceContext);
	}

	public static PowwowSubscriptionSender getPowwowSubscriptionSender(
			long powwowMeetingId, ServiceContext serviceContext)
		throws Exception {

		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		long calendarBookingId = powwowMeeting.getCalendarBookingId();

		return _getSubcriptionSender(powwowMeeting, calendarBookingId,
			serviceContext);
	}

	public static String getRecurrenceSummary(Recurrence recurrence, Locale locale) {

		String recurrenceSummary = StringPool.BLANK;

		if (Validator.isNull(locale)) {
			locale = LocaleThreadLocal.getDefaultLocale();
		}

		StringBuilder sb = new StringBuilder();

		Map<String, Object> valuesMap = new HashMap<>();

		String position = StringPool.BLANK;
		String weekDay = StringPool.BLANK;
		String weekDays = StringPool.BLANK;

		if (recurrence.getInterval() == 1) {
			String recurrenceLabel = StringUtils.capitalize(
				StringUtils.lowerCase(recurrence.getFrequency().getValue()));

			sb.append(recurrenceLabel);
		}
		else {
			sb.append(LanguageUtil.get(locale, "every") + " ${interval} ${frequencyLabel}");

			String frequencyLabel = LanguageUtil.get(locale, _frequencyMapLabel.get(recurrence.getFrequency()));

			valuesMap.put("interval", recurrence.getInterval());
			valuesMap.put("frequencyLabel", frequencyLabel);
		}

		if (Validator.isNotNull(recurrence.getPositionalWeekday()) && recurrence.getFrequency() == Frequency.MONTHLY) {
			PositionalWeekday positionalWeekday = recurrence.getPositionalWeekday();

			sb.append(STR_SPACE + LanguageUtil.get(locale, "on") + " ${position} ${weekDay}");

			position = LanguageUtil.get(locale, _positionMapLabel.get(positionalWeekday.getPosition()));
			weekDay = LanguageUtil.get(locale, _weekDayMapLabel.get(positionalWeekday.getWeekday()));

			valuesMap.put("position", position);
			valuesMap.put("weekDay", weekDay);
		}
		else if (recurrence.getFrequency() == Frequency.WEEKLY && recurrence.getWeekdays().size() > 0) {
			sb.append(STR_SPACE + LanguageUtil.get(locale, "on") + " ${weekDays}");

			List<String> weekdayList = new ArrayList<>();

			for (Weekday day : recurrence.getWeekdays()) {
				weekdayList.add(LanguageUtil.get(locale, _weekDayMapLabel.get(day)));
			}

			weekDays = weekdayList.stream().collect(Collectors.joining(","));

			valuesMap.put("weekDays", weekDays);
		}

		if (recurrence.getCount() > 0) {
			sb.append(", ${count} " + LanguageUtil.get(locale, "times"));

			valuesMap.put("count", recurrence.getCount());
		}
		else if (Validator.isNotNull(recurrence.getUntilJCalendar())) {
			Calendar untilDateJCalendar = recurrence.getUntilJCalendar();

			sb.append(StringPool.COMMA_AND_SPACE);
			sb.append(LanguageUtil.get(locale, "until"));
			sb.append(STR_SPACE);
			sb.append("${untilMonthLabel}");
			sb.append(STR_SPACE);
			sb.append("${untilDay}");
			sb.append(STR_SPACE + ", ");
			sb.append("${untilYear}");

			String untilMonthLabel = _monthMapLabel.get(untilDateJCalendar.get(Calendar.MONTH));

			valuesMap.put("untilMonthLabel", LanguageUtil.get(locale, untilMonthLabel));
			valuesMap.put("untilDay", untilDateJCalendar.get(Calendar.DAY_OF_MONTH));
			valuesMap.put("untilYear", untilDateJCalendar.get(Calendar.YEAR));
		}

		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		recurrenceSummary = sub.replace(sb.toString());

		return recurrenceSummary;
	}

	public static void sendNotifications(
			long powwowMeetingId, ServiceContext serviceContext)
		throws Exception {

		PowwowSubscriptionSender powwowSubscriptionSender =
			getPowwowSubscriptionSender(powwowMeetingId, serviceContext);

		List<PowwowParticipant> powwowParticipants =
			PowwowParticipantLocalServiceUtil.getPowwowParticipants(
				powwowMeetingId);

		for (PowwowParticipant powwowParticipant : powwowParticipants) {
			if (powwowParticipant.getStatus() ==
					PowwowParticipantConstants.STATUS_INVITED) {

				continue;
			}

			powwowSubscriptionSender.addRuntimeSubscribers(
				powwowParticipant.getEmailAddress(),
				powwowParticipant.getName());

			_sendNotificationEvent(powwowParticipant);

			PowwowParticipantLocalServiceUtil.updateStatus(
				powwowParticipant.getPowwowParticipantId(),
				PowwowParticipantConstants.STATUS_INVITED);
		}

		powwowSubscriptionSender.flushNotificationsAsync();
	}

	public static void sendNotificationsToPowwowParticipants(
			long powwowMeetingId, long calendarBookingId, ServiceContext serviceContext)
		throws Exception {
		PowwowSubscriptionSender powwowSubscriptionSender =
			getPowwowSubscriptionSender(powwowMeetingId, calendarBookingId, serviceContext);

		List<PowwowParticipant> powwowParticipants =
			PowwowParticipantLocalServiceUtil.getPowwowParticipants(powwowMeetingId);

		for (PowwowParticipant powwowParticipant : powwowParticipants) {

			powwowSubscriptionSender.addRuntimeSubscribers(
				powwowParticipant.getEmailAddress(),
				powwowParticipant.getName());

			_sendNotificationEvent(powwowParticipant);
		}

		powwowSubscriptionSender.flushNotificationsAsync();
	}

	private static PowwowParticipant _getPowwowParticipant(
		long powwowMeetingId, String name, long participantUserId,
		String emailAddress, int type) {

		PowwowParticipant powwowParticipant = null;

		if (powwowMeetingId > 0) {
			if (participantUserId > 0) {
				powwowParticipant =
					PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
						powwowMeetingId, participantUserId);
			}
			else {
				powwowParticipant =
					PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
						powwowMeetingId, emailAddress);
			}
		}

		if (powwowParticipant == null) {
			powwowParticipant =
				PowwowParticipantLocalServiceUtil.createPowwowParticipant(0);
		}

		powwowParticipant.setName(name);
		powwowParticipant.setParticipantUserId(participantUserId);
		powwowParticipant.setEmailAddress(emailAddress);
		powwowParticipant.setType(type);

		return powwowParticipant;
	}

	private static PowwowSubscriptionSender _getSubcriptionSender(
			PowwowMeeting powwowMeeting, long calendarBookingId,
			ServiceContext serviceContext)
		throws Exception {

		String startDateString = StringPool.BLANK;
		String startTimeString = StringPool.BLANK;
		String timeZoneDisplayName = StringPool.BLANK;
		String recurrenceSumary = StringPool.BLANK;

		if (calendarBookingId > 0) {
			CalendarBooking calendarBooking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(
					calendarBookingId);

			User user = UserLocalServiceUtil.getUser(powwowMeeting.getUserId());

			Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"EEEE, dd MMMMM yyyy", user.getLocale(), user.getTimeZone());

			startDateString = format.format(calendarBooking.getStartTime());

			Format timeFormatDate = FastDateFormatFactoryUtil.getTime(
				user.getLocale(), user.getTimeZone());

			startTimeString = timeFormatDate.format(
				calendarBooking.getStartTime());

			TimeZone timeZone = user.getTimeZone();

			timeZoneDisplayName = timeZone.getDisplayName();

			if (calendarBooking.isRecurring()) {
				recurrenceSumary = "Repeat: " +
					getRecurrenceSummary(calendarBooking.getRecurrenceObj(),
						serviceContext.getLocale());
			}
		}

		PowwowSubscriptionSender powwowSubscriptionSender =
			new PowwowSubscriptionSender();

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				powwowMeeting.getCompanyId(), powwowMeeting.getGroupId(),
				PowwowPortletKeys.PREFS_OWNER_TYPE_GROUP,
				LayoutConstants.DEFAULT_PLID,
				PowwowPortletKeys.POWWOW_MEETINGS);

		if (calendarBookingId > 0) {
			powwowSubscriptionSender.addFileAttachment(
				FileUtil.createTempFile(exportPowwowMeetingCalendar(
					powwowMeeting.getPowwowMeetingId())),
				"invite.ics");
		}

		powwowSubscriptionSender.setCompanyId(powwowMeeting.getCompanyId());

		powwowSubscriptionSender.setContextAttributes(
			"[$MEETING_DATE$]", startDateString, "[$MEETING_DESCRIPTION$]",
			powwowMeeting.getDescription(),
			"[$MEETING_JOIN_BY_PHONE_ACCESS_CODE$]",
			PowwowServiceProviderUtil
				.getJoinByPhoneAccessCode(powwowMeeting.getPowwowMeetingId()),
			"[$MEETING_JOIN_BY_PHONE_ACCESS_CODE_LABEL$]",
			LanguageUtil.get(serviceContext.getLocale(),
				PowwowServiceProviderUtil.getJoinByPhoneAccessCodeLabel()),
			"[$MEETING_NAME$]", powwowMeeting.getName(), "[$MEETING_PASSWORD$]",
			PowwowServiceProviderUtil.getOptionPassword(
				powwowMeeting.getPowwowMeetingId()),
			"[$RECURRENCE_SUMMARY$]", recurrenceSumary,
			"[$MEETING_TIME$]", startTimeString, "[$MEETING_TIME_ZONE$]",
			timeZoneDisplayName, "[$MEETING_URL$]",
			getInvitationURL(
				powwowMeeting.getPowwowMeetingId(), null,
				serviceContext.getRequest()));

		powwowSubscriptionSender.setContextCreatorUserPrefix("MEETING");

		String fromName = PrefsPropsUtil.getString(
			powwowMeeting.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			powwowMeeting.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		powwowSubscriptionSender.setFrom(fromAddress, fromName);

		powwowSubscriptionSender.setHtmlFormat(true);
		powwowSubscriptionSender.setBody(
			portletPreferences.getValue(
				"emailBody_" + powwowMeeting.getLanguageId(),
				ContentUtil.get(
					PowwowUtil.class.getClassLoader(),
					PortletPropsValues.POWWOW_INVITATION_EMAIL_BODY)));
		powwowSubscriptionSender.setSubject(
			portletPreferences.getValue(
				"emailSubject_" + powwowMeeting.getLanguageId(),
				ContentUtil.get(
					PowwowUtil.class.getClassLoader(),
					PortletPropsValues.POWWOW_INVITATION_EMAIL_SUBJECT)));
		powwowSubscriptionSender.setMailId(
			"powwowMeeting", powwowMeeting.getPowwowMeetingId());
		powwowSubscriptionSender.setPortletId(
			PowwowPortletKeys.POWWOW_MEETINGS);
		powwowSubscriptionSender.setReplyToAddress(fromAddress);
		powwowSubscriptionSender.setScopeGroupId(powwowMeeting.getGroupId());
		powwowSubscriptionSender.setServiceContext(serviceContext);
		powwowSubscriptionSender.setUserId(powwowMeeting.getUserId());
		powwowSubscriptionSender.setNotificationType(PowwowParticipantConstants.STATUS_INVITED);
		powwowSubscriptionSender.addPersistedSubscribers(MeetingsPortlet.class.getName(),0);
		powwowSubscriptionSender.setClassPK(powwowMeeting.getPowwowMeetingId());

		return powwowSubscriptionSender;
	}

	private static void _sendNotificationEvent(
			PowwowParticipant powwowParticipant)
		throws Exception {

		if (powwowParticipant.getParticipantUserId() <= 0) {
			return;
		}

		if (!UserNotificationManagerUtil.isDeliver(
				powwowParticipant.getParticipantUserId(),
				PowwowPortletKeys.POWWOW_MEETINGS, 0,
				PowwowParticipantConstants.STATUS_INVITED,
				UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

			return;
		}

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"classPK", powwowParticipant.getPowwowMeetingId());
		notificationEventJSONObject.put(
			"userId", powwowParticipant.getUserId());

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(), PowwowPortletKeys.POWWOW_MEETINGS,
				notificationEventJSONObject);

		notificationEvent.setDeliveryRequired(0);

		UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
			powwowParticipant.getParticipantUserId(), notificationEvent);
	}

	private static final String STR_SPACE = StringPool.SPACE;

	private static final Map<Frequency, String> _frequencyMapLabel = new HashMap<>();

	private static final Map<Integer, String> _monthMapLabel = new HashMap<>();

	private static final Map<Integer, String> _positionMapLabel = new HashMap<>();

	private static final Map<Weekday, String> _weekDayMapLabel = new HashMap<>();

	static {
		_frequencyMapLabel.put(Frequency.DAILY, "days");
		_frequencyMapLabel.put(Frequency.WEEKLY, "weeks");
		_frequencyMapLabel.put(Frequency.MONTHLY, "months");

		_monthMapLabel.put(0, "january");
		_monthMapLabel.put(1, "february");
		_monthMapLabel.put(2, "march");
		_monthMapLabel.put(3, "april");
		_monthMapLabel.put(4, "may");
		_monthMapLabel.put(5, "june");
		_monthMapLabel.put(6, "july");
		_monthMapLabel.put(7, "august");
		_monthMapLabel.put(8, "september");
		_monthMapLabel.put(9, "october");
		_monthMapLabel.put(10, "november");
		_monthMapLabel.put(11, "december");

		_positionMapLabel.put(-1, "last");
		_positionMapLabel.put(1, "first");
		_positionMapLabel.put(2, "second");
		_positionMapLabel.put(3, "third");
		_positionMapLabel.put(3, "fourth");

		_weekDayMapLabel.put(Weekday.SUNDAY, "weekday.SU");
		_weekDayMapLabel.put(Weekday.MONDAY, "weekday.MO");
		_weekDayMapLabel.put(Weekday.TUESDAY, "weekday.TU");
		_weekDayMapLabel.put(Weekday.WEDNESDAY, "weekday.WE");
		_weekDayMapLabel.put(Weekday.THURSDAY, "weekday.TH");
		_weekDayMapLabel.put(Weekday.FRIDAY, "weekday.FR");
		_weekDayMapLabel.put(Weekday.SATURDAY, "weekday.SA");
	}
}