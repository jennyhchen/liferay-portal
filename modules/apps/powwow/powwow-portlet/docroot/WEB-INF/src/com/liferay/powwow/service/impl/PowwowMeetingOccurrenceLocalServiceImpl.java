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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.service.base.PowwowMeetingOccurrenceLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the powwow meeting occurrence local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.powwow.service.PowwowMeetingOccurrenceLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Vu Ho
 * @see PowwowMeetingOccurrenceLocalServiceBaseImpl
 */
public class PowwowMeetingOccurrenceLocalServiceImpl
	extends PowwowMeetingOccurrenceLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowMeetingOccurrence addPowwowMeetingOccurrence(
			long userId, String occurrenceApiId, long powwowMeetingId,
			OccurrenceStatus occurrenceStatus, String zoomOriginalData,
			long startTime, long endTime)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long occurrenceId = counterLocalService.increment(getModelClassName());

		PowwowMeetingOccurrence powwowMeetingOccurrence =
			powwowMeetingOccurrencePersistence.create(occurrenceId);

		powwowMeetingOccurrence.setCompanyId(user.getCompanyId());
		powwowMeetingOccurrence.setUserId(user.getUserId());
		powwowMeetingOccurrence.setUserName(user.getFullName());
		powwowMeetingOccurrence.setCreateDate(now);
		powwowMeetingOccurrence.setModifiedDate(now);

		powwowMeetingOccurrence.setPowwowMeetingId(powwowMeetingId);
		powwowMeetingOccurrence.setOccurrenceStatus(occurrenceStatus.getValue());
		powwowMeetingOccurrence.setZoomOriginalData(zoomOriginalData);
		powwowMeetingOccurrence.setCalendarBookingId(0);
		powwowMeetingOccurrence.setOccurrenceApiId(occurrenceApiId);
		powwowMeetingOccurrence.setStartTime(startTime);
		powwowMeetingOccurrence.setEndTime(endTime);

		powwowMeetingOccurrencePersistence.update(powwowMeetingOccurrence);

		return powwowMeetingOccurrence;
	}

	public void deleteByPowwowMeetingId(long powwowMeetingId) {

		// TODO delete CalendarBooking belongs to this occurrence

		List<PowwowMeetingOccurrence> meetingOccurrences =
			powwowMeetingOccurrencePersistence
				.findByPowwowMeetingId(powwowMeetingId);

		for (PowwowMeetingOccurrence meetingOccurrence : meetingOccurrences) {
			powwowMeetingOccurrenceLocalService
				.deletePowwowMeetingOccurrence(meetingOccurrence);
		}
	}

	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId) {

		return powwowMeetingOccurrencePersistence
			.findByPowwowMeetingId(powwowMeetingId, 0, 100);
	}
}