package com.liferay.powwow.meetings.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.service.PowwowMeetingOccurrenceLocalServiceUtil;
import java.util.List;

public class SynchronizeOccurrenceStatusListener extends BaseMessageListener{

	@Override
	protected void doReceive(Message message) throws Exception {
		updateOccurrenceStatus();
	}

	private void updateOccurrenceStatus() {
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
			}

			start += delta;
			end += delta;
		}
	}
}
