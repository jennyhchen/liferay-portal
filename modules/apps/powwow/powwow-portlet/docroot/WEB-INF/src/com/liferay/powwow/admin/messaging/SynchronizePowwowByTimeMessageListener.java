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

package com.liferay.powwow.admin.messaging;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowMeetingConstants;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;

import java.util.List;

/**
 * Background job to update PowwowMeeting that has end time passed
 * automatically. Separated from {@link SynchronizePowwowMessageListener},
 * because this job may process larger number of records, and its interval is
 * less often than the other job.
 *
 * @author Tang Hieu Ha
 */
public class SynchronizePowwowByTimeMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {

		_processPassedMeetings();
	}

	private void _processPassedMeetings() throws PortalException {

		// Restrict maximum number of records, so that it will not cost much
		// time of processing

		int count = _MAX_NUMBER_PROCESSED;
		int delta = 50;
		int start = 0;
		int end = delta;

		List<PowwowMeeting> powwowMeetings;
		CalendarBooking calendarBooking;
		long now = System.currentTimeMillis();

		while (start < count) {

			// TODO improve performance by custom SQL

			powwowMeetings = PowwowMeetingLocalServiceUtil.getPowwowMeetings(
				PowwowMeetingConstants.STATUS_SCHEDULED, start, end);

			if (powwowMeetings.isEmpty()) {
				break;
			}

			for (PowwowMeeting powwowMeeting : powwowMeetings) {

				calendarBooking = CalendarBookingLocalServiceUtil
					.fetchCalendarBooking(powwowMeeting.getCalendarBookingId());

				boolean nonRecurringPassed = !calendarBooking.isRecurring() &&
					calendarBooking.getEndTime() < now;

				boolean noOccurrenceAvailable = calendarBooking.isRecurring() &&
					Validator.isNull(powwowMeeting.findNextOccurrence());

				if (nonRecurringPassed || noOccurrenceAvailable) {

					PowwowMeetingLocalServiceUtil.updateStatus(
						powwowMeeting.getPowwowMeetingId(),
						PowwowMeetingConstants.STATUS_COMPLETED);
				}
			}

			start += delta;
			end += delta;
		}
	}

	private static final Log _log =
		LogFactoryUtil.getLog(SynchronizePowwowByTimeMessageListener.class);

	private static final int _MAX_NUMBER_PROCESSED = 1000;
}