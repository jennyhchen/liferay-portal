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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.occurrence.OccurrenceStatus;

import java.util.List;

/**
 * Provides the remote service interface for PowwowMeetingOccurrence. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface PowwowMeetingOccurrenceService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PowwowMeetingOccurrenceServiceUtil} to access the powwow meeting occurrence remote service. Add custom service methods to <code>com.liferay.powwow.service.impl.PowwowMeetingOccurrenceServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public PowwowMeetingOccurrence addPowwowMeetingOccurrence(
			long groupId, String occurrenceApiId, long powwowMeetingId,
			OccurrenceStatus occurrenceStatus, String zoomOriginalData,
			long startTime, long endTime)
		throws PortalException;

	public void deleteByPowwowMeetingId(long powwowMeetingId)
		throws PortalException;

	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
			long powwowMeetingId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public PowwowMeetingOccurrence updateOccurrenceStatus(
			long powwowMeetingId, long occurrenceId,
			OccurrenceStatus occurrenceStatus)
		throws PortalException;

	public PowwowMeetingOccurrence updateOccurrenceTime(
			long powwowMeetingId, long occurrenceId, long startTime,
			long endTime, long calendarBookingId)
		throws PortalException;

}