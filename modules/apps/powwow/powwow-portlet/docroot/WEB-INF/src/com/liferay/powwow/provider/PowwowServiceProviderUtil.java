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

package com.liferay.powwow.provider;

import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowServer;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Shinn Lok
 * @author Marco Calderon
 */
public class PowwowServiceProviderUtil {

	public static Map<String, Serializable> addPowwowMeeting(
			long userId, long powwowServerId, long powwowMeetingId, String name,
			Map<String, String> options)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.addPowwowMeeting(
			userId, powwowServerId, powwowMeetingId, name, options);
	}

	public static PowwowMeeting deletePowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.deletePowwowMeeting(powwowMeetingId);
	}

	public static PowwowMeeting endPowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.endPowwowMeeting(powwowMeetingId);
	}

	public static int getAddPowwowMeetingStrategy() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getAddPowwowMeetingStrategy();
	}

	public static List<String> getBrandingFeatures() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getBrandingFeatures();
	}

	public static String getBrandingLabel() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getBrandingLabel();
	}

	public static Map<String, String> getIndexFields(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getIndexFields(powwowMeetingId);
	}

	public static long getJoinByPhoneAccessCode(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getJoinByPhoneAccessCode(powwowMeetingId);
	}

	public static String getJoinByPhoneAccessCodeLabel() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getJoinByPhoneAccessCodeLabel();
	}

	public static List<String> getJoinByPhoneDefaultNumbers() {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getJoinByPhoneDefaultNumbers();
	}

	public static Map<String, List<String>> getJoinByPhoneInternationalNumbers() {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getJoinByPhoneInternationalNumbers();
	}

	public static String getJoinPowwowMeetingURL(
			long powwowMeetingId, String name, int type)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getJoinPowwowMeetingURL(
			powwowMeetingId, name, type);
	}

	public static boolean getOptionAutoStartVideo(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getOptionAutoStartVideo(powwowMeetingId);
	}

	public static String getOptionPassword(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getOptionPassword(powwowMeetingId);
	}

	public static long getPowwowServerId() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getPowwowServerId();
	}

	public static String getPowwowServiceProviderName() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.getPowwowServiceProviderName();
	}

	public static boolean isFieldAPIKeyRequired() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isFieldAPIKeyRequired();
	}

	public static boolean isFieldSecretRequired() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isFieldSecretRequired();
	}

	public static boolean isPowwowMeetingCreated(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isPowwowMeetingCreated(powwowMeetingId);
	}

	public static boolean isPowwowMeetingRunning(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isPowwowMeetingRunning(powwowMeetingId);
	}

	public static boolean isServerActive(PowwowServer powwowServer) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isServerActive(powwowServer);
	}

	public static boolean isSupportsJoinByPhone() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isSupportsJoinByPhone();
	}

	public static boolean isSupportsOptionAutoStartVideo() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isSupportsOptionAutoStartVideo();
	}

	public static boolean isSupportsOptionPassword() {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isSupportsOptionPassword();
	}

	public static boolean isSupportsPresettingParticipantName() {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.isSupportsPresettingParticipantName();
	}

	public static final String toZoomDateTimeUTC(Calendar calendar) {

		TimeZone utcTimeZone = TimeZone.getTimeZone(StringPool.UTC);

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(_ZOOM_UTC_DATETIME_PATTERN, utcTimeZone);

		return dateFormat.format(JCalendarUtil.getJCalendar(calendar, utcTimeZone).getTime());
	}

	public static Map<String, Serializable> updatePowwowMeeting(
			long powwowMeetingId, String name, long userId,
			Map<String, String> options)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider();

		return powwowServiceProvider.updatePowwowMeeting(
			powwowMeetingId, name, userId, options);
	}

	protected static PowwowServiceProvider getPowwowServiceProvider() {

		return PowwowServiceProviderFactory.getPowwowServiceProvider();
	}

	private static String _ZOOM_UTC_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
}