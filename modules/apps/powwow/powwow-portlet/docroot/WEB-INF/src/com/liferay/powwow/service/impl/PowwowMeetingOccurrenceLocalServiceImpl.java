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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.service.base.PowwowMeetingOccurrenceLocalServiceBaseImpl;
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

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.powwow.service.PowwowMeetingOccurrenceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.powwow.service.PowwowMeetingOccurrenceLocalServiceUtil</code>.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowMeetingOccurrence addPowwowMeetingOccurrence(
		long powwowMeetingId, String occurrenceId, String occurrenceStatus, String zoomOriginalData, long startTime,
		long endTime, long calendarBookingId) throws PortalException {

		PowwowMeetingOccurrence powwowMeetingOccurrence = powwowMeetingOccurrencePersistence.create(occurrenceId);

		powwowMeetingOccurrence.setPowwowMeetingId(powwowMeetingId);
		powwowMeetingOccurrence.setOccurrenceStatus(occurrenceStatus);
		powwowMeetingOccurrence.setZoomOriginalData(zoomOriginalData);
		powwowMeetingOccurrence.setCalendarBookingId(calendarBookingId);
		powwowMeetingOccurrence.setStartTime(startTime);
		powwowMeetingOccurrence.setEndTime(endTime);

		powwowMeetingOccurrencePersistence.update(powwowMeetingOccurrence);

		return powwowMeetingOccurrence;
	}

	@Indexable(type = IndexableType.DELETE)
	public void deleteByPowwowMeetingId (long powwowMeetingId) {
		List<PowwowMeetingOccurrence> meetingOccurrences =
			powwowMeetingOccurrencePersistence.findByPowwowMeetingId(powwowMeetingId);

		for(PowwowMeetingOccurrence meetingOccurrence: meetingOccurrences) {
			powwowMeetingOccurrencePersistence.remove(meetingOccurrence);
		}
	}

	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(long powwowMeetingId){
		return powwowMeetingOccurrencePersistence.findByPowwowMeetingId(powwowMeetingId);
	}
}