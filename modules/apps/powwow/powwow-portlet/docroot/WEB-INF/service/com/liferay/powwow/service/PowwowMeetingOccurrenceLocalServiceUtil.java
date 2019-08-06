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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * Provides the local service utility for PowwowMeetingOccurrence. This utility wraps
 * <code>com.liferay.powwow.service.impl.PowwowMeetingOccurrenceLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceLocalService
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.powwow.service.impl.PowwowMeetingOccurrenceLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			addPowwowMeetingOccurrence(
				long userId, String occurrenceApiId, long powwowMeetingId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
				String zoomOriginalData, long startTime, long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addPowwowMeetingOccurrence(
			userId, occurrenceApiId, powwowMeetingId, occurrenceStatus,
			zoomOriginalData, startTime, endTime);
	}

	/**
	 * Adds the powwow meeting occurrence to the database. Also notifies the appropriate model listeners.
	 *
	 * @param powwowMeetingOccurrence the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was added
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		addPowwowMeetingOccurrence(
			com.liferay.powwow.model.PowwowMeetingOccurrence
				powwowMeetingOccurrence) {

		return getService().addPowwowMeetingOccurrence(powwowMeetingOccurrence);
	}

	public static int countByStatusAndEndTimeLE(
		com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
		long maxEndTime) {

		return getService().countByStatusAndEndTimeLE(
			occurrenceStatus, maxEndTime);
	}

	/**
	 * Creates a new powwow meeting occurrence with the primary key. Does not add the powwow meeting occurrence to the database.
	 *
	 * @param occurrenceId the primary key for the new powwow meeting occurrence
	 * @return the new powwow meeting occurrence
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		createPowwowMeetingOccurrence(long occurrenceId) {

		return getService().createPowwowMeetingOccurrence(occurrenceId);
	}

	public static void deleteByPowwowMeetingId(long powwowMeetingId) {
		getService().deleteByPowwowMeetingId(powwowMeetingId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the powwow meeting occurrence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was removed
	 * @throws PortalException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			deletePowwowMeetingOccurrence(long occurrenceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePowwowMeetingOccurrence(occurrenceId);
	}

	/**
	 * Deletes the powwow meeting occurrence from the database. Also notifies the appropriate model listeners.
	 *
	 * @param powwowMeetingOccurrence the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was removed
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		deletePowwowMeetingOccurrence(
			com.liferay.powwow.model.PowwowMeetingOccurrence
				powwowMeetingOccurrence) {

		return getService().deletePowwowMeetingOccurrence(
			powwowMeetingOccurrence);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		fetchPowwowMeetingOccurrence(long occurrenceId) {

		return getService().fetchPowwowMeetingOccurrence(occurrenceId);
	}

	public static java.util.List
		<com.liferay.powwow.model.PowwowMeetingOccurrence>
			findByPowwowMeetingId(long powwowMeetingId) {

		return getService().findByPowwowMeetingId(powwowMeetingId);
	}

	public static java.util.List
		<com.liferay.powwow.model.PowwowMeetingOccurrence>
			findByPowwowMeetingIdAndStatusAndEndTimeGE(
				long powwowMeetingId,
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
				long maxEndTime, int start, int end) {

		return getService().findByPowwowMeetingIdAndStatusAndEndTimeGE(
			powwowMeetingId, occurrenceStatus, maxEndTime, start, end);
	}

	public static java.util.List
		<com.liferay.powwow.model.PowwowMeetingOccurrence>
			findByStatusAndEndTimeLE(
				com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus,
				long maxEndTime, int start, int end) {

		return getService().findByStatusAndEndTimeLE(
			occurrenceStatus, maxEndTime, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence
	 * @throws PortalException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
			getPowwowMeetingOccurrence(long occurrenceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPowwowMeetingOccurrence(occurrenceId);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of powwow meeting occurrences
	 */
	public static java.util.List
		<com.liferay.powwow.model.PowwowMeetingOccurrence>
			getPowwowMeetingOccurrences(int start, int end) {

		return getService().getPowwowMeetingOccurrences(start, end);
	}

	/**
	 * Returns the number of powwow meeting occurrences.
	 *
	 * @return the number of powwow meeting occurrences
	 */
	public static int getPowwowMeetingOccurrencesCount() {
		return getService().getPowwowMeetingOccurrencesCount();
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		updateOccurrenceStatus(
			long occurrenceId,
			com.liferay.powwow.occurrence.OccurrenceStatus occurrenceStatus) {

		return getService().updateOccurrenceStatus(
			occurrenceId, occurrenceStatus);
	}

	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		updateOccurrenceTime(
			long occurrenceId, long startTime, long endTime,
			long calendarBookingId) {

		return getService().updateOccurrenceTime(
			occurrenceId, startTime, endTime, calendarBookingId);
	}

	/**
	 * Updates the powwow meeting occurrence in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param powwowMeetingOccurrence the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was updated
	 */
	public static com.liferay.powwow.model.PowwowMeetingOccurrence
		updatePowwowMeetingOccurrence(
			com.liferay.powwow.model.PowwowMeetingOccurrence
				powwowMeetingOccurrence) {

		return getService().updatePowwowMeetingOccurrence(
			powwowMeetingOccurrence);
	}

	public static void clearService() {
		_service = null;
	}

	public static PowwowMeetingOccurrenceLocalService getService() {
		if (_service == null) {
			_service =
				(PowwowMeetingOccurrenceLocalService)
					PortletBeanLocatorUtil.locate(
						ServletContextUtil.getServletContextName(),
						PowwowMeetingOccurrenceLocalService.class.getName());
		}

		return _service;
	}

	private static PowwowMeetingOccurrenceLocalService _service;

}