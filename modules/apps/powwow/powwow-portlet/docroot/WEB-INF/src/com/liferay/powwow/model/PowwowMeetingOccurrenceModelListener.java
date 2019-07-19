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

package com.liferay.powwow.model;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.powwow.occurrence.OccurrenceStatus;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;

/**
 * @author Tang Hieu Ha
 */
public class PowwowMeetingOccurrenceModelListener
	extends BaseModelListener<PowwowMeetingOccurrence> {

	@Override
	public void onAfterUpdate(PowwowMeetingOccurrence model)
		throws ModelListenerException {

		OccurrenceStatus occurrenceStatus = model.getOccurrenceStatusEnum();

		if (occurrenceStatus != OccurrenceStatus.DELETE) {
			return;
		}

		try {
			PowwowMeeting powwowMeeting =
				PowwowMeetingLocalServiceUtil.getPowwowMeeting(
					model.getPowwowMeetingId());

			if (powwowMeeting.findNextOccurrence() != null) {
				return;
			}

			powwowMeeting.setStatus(PowwowMeetingConstants.STATUS_COMPLETED);

			PowwowMeetingLocalServiceUtil.updatePowwowMeeting(powwowMeeting);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PowwowMeetingOccurrenceModelListener.class);

}