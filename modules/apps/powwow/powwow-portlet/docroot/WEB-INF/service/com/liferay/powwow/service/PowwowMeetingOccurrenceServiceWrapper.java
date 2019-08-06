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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PowwowMeetingOccurrenceService}.
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceService
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceServiceWrapper
	implements PowwowMeetingOccurrenceService,
			   ServiceWrapper<PowwowMeetingOccurrenceService> {

	public PowwowMeetingOccurrenceServiceWrapper(
		PowwowMeetingOccurrenceService powwowMeetingOccurrenceService) {

		_powwowMeetingOccurrenceService = powwowMeetingOccurrenceService;
	}

	@Override
	public com.liferay.powwow.model.PowwowMeetingOccurrence
			addPowwowMeetingOccurrence(
				long groupId, String occurrenceApiId, long powwowMeetingId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
				String zoomOriginalData, long startTime, long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowMeetingOccurrenceService.addPowwowMeetingOccurrence(
			groupId, occurrenceApiId, powwowMeetingId, occurrenceStatus,
			zoomOriginalData, startTime, endTime);
	}

	@Override
	public void deleteByPowwowMeetingId(long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_powwowMeetingOccurrenceService.deleteByPowwowMeetingId(
			powwowMeetingId);
	}

	@Override
	public java.util.List<com.liferay.powwow.model.PowwowMeetingOccurrence>
			findByPowwowMeetingId(long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowMeetingOccurrenceService.findByPowwowMeetingId(
			powwowMeetingId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _powwowMeetingOccurrenceService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.powwow.model.PowwowMeetingOccurrence
			updateOccurrenceStatus(
				long powwowMeetingId, long occurrenceId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowMeetingOccurrenceService.updateOccurrenceStatus(
			powwowMeetingId, occurrenceId, occurrenceStatus);
	}

	@Override
	public com.liferay.powwow.model.PowwowMeetingOccurrence
			updateOccurrenceTime(
				long powwowMeetingId, long occurrenceId, long startTime,
				long endTime, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowMeetingOccurrenceService.updateOccurrenceTime(
			powwowMeetingId, occurrenceId, startTime, endTime,
			calendarBookingId);
	}

	@Override
	public PowwowMeetingOccurrenceService getWrappedService() {
		return _powwowMeetingOccurrenceService;
	}

	@Override
	public void setWrappedService(
		PowwowMeetingOccurrenceService powwowMeetingOccurrenceService) {

		_powwowMeetingOccurrenceService = powwowMeetingOccurrenceService;
	}

	private PowwowMeetingOccurrenceService _powwowMeetingOccurrenceService;

}