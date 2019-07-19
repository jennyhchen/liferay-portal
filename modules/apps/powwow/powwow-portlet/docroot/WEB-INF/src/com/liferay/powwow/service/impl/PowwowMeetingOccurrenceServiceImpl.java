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

package com.liferay.powwow.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.service.base.PowwowMeetingOccurrenceServiceBaseImpl;
import com.liferay.powwow.service.permission.MeetingsPermission;
import com.liferay.powwow.service.permission.PowwowMeetingPermission;
import com.liferay.powwow.util.ActionKeys;

import java.util.List;

/**
 * The implementation of the powwow meeting occurrence remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.powwow.service.PowwowMeetingOccurrenceService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Tang Hieu Ha
 * @see PowwowMeetingOccurrenceServiceBaseImpl
 */
public class PowwowMeetingOccurrenceServiceImpl
	extends PowwowMeetingOccurrenceServiceBaseImpl {

	public PowwowMeetingOccurrence addPowwowMeetingOccurrence(
			long groupId, String occurrenceApiId, long powwowMeetingId,
			OccurrenceStatus occurrenceStatus, String zoomOriginalData,
			long startTime, long endTime)
		throws PortalException {

		MeetingsPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_MEETING);

		return powwowMeetingOccurrenceLocalService.addPowwowMeetingOccurrence(
			getUserId(), occurrenceApiId, powwowMeetingId, occurrenceStatus,
			zoomOriginalData, startTime, endTime);
	}

	public void deleteByPowwowMeetingId(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.DELETE);

		powwowMeetingOccurrenceLocalService.deleteByPowwowMeetingId(
			powwowMeetingId);
	}

	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
			long powwowMeetingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.VIEW);

		return powwowMeetingOccurrenceLocalService.findByPowwowMeetingId(
			powwowMeetingId);
	}

	public PowwowMeetingOccurrence updateOccurrenceStatus(
			long powwowMeetingId, long occurrenceId,
			OccurrenceStatus occurrenceStatus)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.UPDATE);

		return powwowMeetingOccurrenceLocalService.updateOccurrenceStatus(
			occurrenceId, occurrenceStatus);
	}

	public PowwowMeetingOccurrence updateOccurrenceTime(
			long powwowMeetingId, long occurrenceId, long startTime,
			long endTime, long calendarBookingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.UPDATE);

		return powwowMeetingOccurrenceLocalService.updateOccurrenceTime(
			occurrenceId, startTime, endTime, calendarBookingId);
	}

}