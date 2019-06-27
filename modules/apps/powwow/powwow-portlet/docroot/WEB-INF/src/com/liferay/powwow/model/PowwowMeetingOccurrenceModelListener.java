package com.liferay.powwow.model;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;

public class PowwowMeetingOccurrenceModelListener
	extends BaseModelListener<PowwowMeetingOccurrence> {

	@Override
	public void onAfterUpdate(PowwowMeetingOccurrence model)
		throws ModelListenerException {

		if (!model.getOccurrenceStatusEnum().equals(OccurrenceStatus.DELETE)) {
			return;
		}

		try {
			PowwowMeeting powwowMeeting = PowwowMeetingLocalServiceUtil
				.getPowwowMeeting(model.getPowwowMeetingId());
			boolean hasNextOccurrence =
				Validator.isNotNull(powwowMeeting.findNextOccurrence());

			if (hasNextOccurrence) {
				return;
			}

			powwowMeeting.setStatus(PowwowMeetingConstants.STATUS_COMPLETED);
			PowwowMeetingLocalServiceUtil.updatePowwowMeeting(powwowMeeting);
		}
		catch (PortalException e) {
			_log.error(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(PowwowMeetingOccurrenceModelListener.class);
}
