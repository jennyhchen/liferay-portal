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

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.PowwowMeetingOccurrenceLocalServiceUtil;
import com.liferay.powwow.util.PowwowUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vu Ho
 */
public class SynchronizeOccurrenceStatusListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		_updateOccurrenceStatus();
	}

	private void _updateOccurrenceStatus() {
		Set<Long> powwowMeetingIds = new HashSet<>();

		long now = System.currentTimeMillis();
		int delta = 50;
		int count =
			PowwowMeetingOccurrenceLocalServiceUtil.countByStatusAndEndTimeLE(
				OccurrenceStatus.AVAILABLE, now);

		int start = 0;
		int end = delta;

		while (start < count) {
			List<PowwowMeetingOccurrence> powwowMeetingOccurrences =
				PowwowMeetingOccurrenceLocalServiceUtil.
					findByStatusAndEndTimeLE(
						OccurrenceStatus.AVAILABLE, System.currentTimeMillis(),
						start, end);

			for (PowwowMeetingOccurrence powwowMeetingOccurrence :
					powwowMeetingOccurrences) {

				PowwowMeetingOccurrenceLocalServiceUtil.updateOccurrenceStatus(
					powwowMeetingOccurrence.getOccurrenceId(),
					OccurrenceStatus.COMPLETED);

				powwowMeetingIds.add(
					powwowMeetingOccurrence.getPowwowMeetingId());
			}

			start += delta;
			end += delta;
		}

		for (Long powwowMeetingId : powwowMeetingIds) {
			PowwowMeeting powwowMeeting =
				PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(
					powwowMeetingId);

			PowwowUtil.reindexPowwowMeeting(powwowMeeting);
		}
	}

}