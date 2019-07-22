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

package com.liferay.powwow.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.powwow.service.PowwowMeetingOccurrenceServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>PowwowMeetingOccurrenceServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceServiceSoap
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceServiceHttp {

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			addPowwowMeetingOccurrence(
				HttpPrincipal httpPrincipal, long groupId,
				String occurrenceApiId, long powwowMeetingId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
				String zoomOriginalData, long startTime, long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowMeetingOccurrenceServiceUtil.class,
				"addPowwowMeetingOccurrence",
				_addPowwowMeetingOccurrenceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, occurrenceApiId, powwowMeetingId,
				occurrenceStatus, zoomOriginalData, startTime, endTime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.powwow.model.PowwowMeetingOccurrence)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteByPowwowMeetingId(
			HttpPrincipal httpPrincipal, long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowMeetingOccurrenceServiceUtil.class,
				"deleteByPowwowMeetingId",
				_deleteByPowwowMeetingIdParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowMeetingId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.powwow.model.PowwowMeetingOccurrence>
				findByPowwowMeetingId(
					HttpPrincipal httpPrincipal, long powwowMeetingId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowMeetingOccurrenceServiceUtil.class,
				"findByPowwowMeetingId", _findByPowwowMeetingIdParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowMeetingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.powwow.model.PowwowMeetingOccurrence>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			updateOccurrenceStatus(
				HttpPrincipal httpPrincipal, long powwowMeetingId,
				long occurrenceId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowMeetingOccurrenceServiceUtil.class,
				"updateOccurrenceStatus",
				_updateOccurrenceStatusParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowMeetingId, occurrenceId, occurrenceStatus);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.powwow.model.PowwowMeetingOccurrence)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			updateOccurrenceTime(
				HttpPrincipal httpPrincipal, long powwowMeetingId,
				long occurrenceId, long startTime, long endTime,
				long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowMeetingOccurrenceServiceUtil.class,
				"updateOccurrenceTime", _updateOccurrenceTimeParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowMeetingId, occurrenceId, startTime, endTime,
				calendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.powwow.model.PowwowMeetingOccurrence)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PowwowMeetingOccurrenceServiceHttp.class);

	private static final Class<?>[] _addPowwowMeetingOccurrenceParameterTypes0 =
		new Class[] {
			long.class, String.class, long.class,
			com.liferay.powwow.occurrence.OccurrenceStatus.class, String.class,
			long.class, long.class
		};
	private static final Class<?>[] _deleteByPowwowMeetingIdParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _findByPowwowMeetingIdParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _updateOccurrenceStatusParameterTypes3 =
		new Class[] {
			long.class, long.class,
			com.liferay.powwow.occurrence.OccurrenceStatus.class
		};
	private static final Class<?>[] _updateOccurrenceTimeParameterTypes4 =
		new Class[] {
			long.class, long.class, long.class, long.class, long.class
		};

}