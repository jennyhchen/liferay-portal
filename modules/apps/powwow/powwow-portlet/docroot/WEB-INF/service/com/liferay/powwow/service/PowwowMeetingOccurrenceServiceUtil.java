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

package com.liferay.powwow.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * Provides the remote service utility for PowwowMeetingOccurrence. This utility wraps
 * <code>com.liferay.powwow.service.impl.PowwowMeetingOccurrenceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceService
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.powwow.service.impl.PowwowMeetingOccurrenceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			addPowwowMeetingOccurrence(
				long groupId, String occurrenceApiId, long powwowMeetingId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
				String zoomOriginalData, long startTime, long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addPowwowMeetingOccurrence(
			groupId, occurrenceApiId, powwowMeetingId, occurrenceStatus,
			zoomOriginalData, startTime, endTime);
	}

	public static void deleteByPowwowMeetingId(long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteByPowwowMeetingId(powwowMeetingId);
	}

	public static java.util.List
		<com.liferay.powwow.model.PowwowMeetingOccurrence>
				findByPowwowMeetingId(long powwowMeetingId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().findByPowwowMeetingId(powwowMeetingId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			updateOccurrenceStatus(
				long powwowMeetingId, long occurrenceId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateOccurrenceStatus(
			powwowMeetingId, occurrenceId, occurrenceStatus);
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			updateOccurrenceTime(
				long powwowMeetingId, long occurrenceId, long startTime,
				long endTime, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateOccurrenceTime(
			powwowMeetingId, occurrenceId, startTime, endTime,
			calendarBookingId);
	}

	public static void clearService() {
		_service = null;
	}

	public static PowwowMeetingOccurrenceService getService() {
		if (_service == null) {
			_service =
				(PowwowMeetingOccurrenceService)PortletBeanLocatorUtil.locate(
					ServletContextUtil.getServletContextName(),
					PowwowMeetingOccurrenceService.class.getName());
		}

		return _service;
	}

	private static PowwowMeetingOccurrenceService _service;

}