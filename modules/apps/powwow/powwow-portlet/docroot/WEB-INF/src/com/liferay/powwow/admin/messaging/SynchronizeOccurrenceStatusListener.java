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

public class SynchronizeOccurrenceStatusListener extends BaseMessageListener{

	@Override
	protected void doReceive(Message message) throws Exception {
		updateOccurrenceStatus();
	}

	private void updateOccurrenceStatus() {
		Set<Long> powwowMeetingIds = new HashSet<>();

		long now = System.currentTimeMillis();
		int delta = 50;
		int count =
			PowwowMeetingOccurrenceLocalServiceUtil
				.countByStatusAndEndTimeLE(OccurrenceStatus.AVAILABLE, now);

		int start = 0;
		int end = delta;

		while (start < count) {
			List<PowwowMeetingOccurrence> powwowMeetingOccurrences =
				PowwowMeetingOccurrenceLocalServiceUtil
					.findByStatusAndEndTimeLE(
						OccurrenceStatus.AVAILABLE, System.currentTimeMillis(), start, end);

			for (PowwowMeetingOccurrence powwowMeetingOccurrence : powwowMeetingOccurrences) {
				PowwowMeetingOccurrenceLocalServiceUtil
					.updateOccurrenceStatus(
						powwowMeetingOccurrence.getOccurrenceId(),
						OccurrenceStatus.COMPLETED);

				powwowMeetingIds.add(powwowMeetingOccurrence.getPowwowMeetingId());
			}

			start += delta;
			end += delta;
		}

		for (Long powwowMeetingId: powwowMeetingIds) {
			PowwowMeeting powwowMeeting =
				PowwowMeetingLocalServiceUtil.fetchPowwowMeeting(powwowMeetingId);

			PowwowUtil.reindexPowwowMeeting(powwowMeeting);
		}
	}
}
