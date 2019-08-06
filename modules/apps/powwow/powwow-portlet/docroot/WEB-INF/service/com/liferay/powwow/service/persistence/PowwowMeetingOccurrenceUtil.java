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

package com.liferay.powwow.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.powwow.model.PowwowMeetingOccurrence;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the powwow meeting occurrence service. This utility wraps <code>com.liferay.powwow.service.persistence.impl.PowwowMeetingOccurrencePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrencePersistence
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		getPersistence().clearCache(powwowMeetingOccurrence);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, PowwowMeetingOccurrence> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PowwowMeetingOccurrence> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PowwowMeetingOccurrence> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PowwowMeetingOccurrence> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PowwowMeetingOccurrence update(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		return getPersistence().update(powwowMeetingOccurrence);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PowwowMeetingOccurrence update(
		PowwowMeetingOccurrence powwowMeetingOccurrence,
		ServiceContext serviceContext) {

		return getPersistence().update(powwowMeetingOccurrence, serviceContext);
	}

	/**
	 * Returns all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @return the matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId) {

		return getPersistence().findByPowwowMeetingId(powwowMeetingId);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end) {

		return getPersistence().findByPowwowMeetingId(
			powwowMeetingId, start, end);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().findByPowwowMeetingId(
			powwowMeetingId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByPowwowMeetingId(
			powwowMeetingId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence findByPowwowMeetingId_First(
			long powwowMeetingId,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPowwowMeetingId_First(
			powwowMeetingId, orderByComparator);
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence fetchByPowwowMeetingId_First(
		long powwowMeetingId,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().fetchByPowwowMeetingId_First(
			powwowMeetingId, orderByComparator);
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence findByPowwowMeetingId_Last(
			long powwowMeetingId,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPowwowMeetingId_Last(
			powwowMeetingId, orderByComparator);
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence fetchByPowwowMeetingId_Last(
		long powwowMeetingId,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().fetchByPowwowMeetingId_Last(
			powwowMeetingId, orderByComparator);
	}

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static PowwowMeetingOccurrence[] findByPowwowMeetingId_PrevAndNext(
			long occurrenceId, long powwowMeetingId,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPowwowMeetingId_PrevAndNext(
			occurrenceId, powwowMeetingId, orderByComparator);
	}

	/**
	 * Removes all the powwow meeting occurrences where powwowMeetingId = &#63; from the database.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 */
	public static void removeByPowwowMeetingId(long powwowMeetingId) {
		getPersistence().removeByPowwowMeetingId(powwowMeetingId);
	}

	/**
	 * Returns the number of powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @return the number of matching powwow meeting occurrences
	 */
	public static int countByPowwowMeetingId(long powwowMeetingId) {
		return getPersistence().countByPowwowMeetingId(powwowMeetingId);
	}

	/**
	 * Returns all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime) {

		return getPersistence().findByOS_ET(occurrenceStatus, endTime);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end) {

		return getPersistence().findByOS_ET(
			occurrenceStatus, endTime, start, end);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().findByOS_ET(
			occurrenceStatus, endTime, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByOS_ET(
			occurrenceStatus, endTime, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence findByOS_ET_First(
			String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByOS_ET_First(
			occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence fetchByOS_ET_First(
		String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().fetchByOS_ET_First(
			occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence findByOS_ET_Last(
			String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByOS_ET_Last(
			occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence fetchByOS_ET_Last(
		String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().fetchByOS_ET_Last(
			occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static PowwowMeetingOccurrence[] findByOS_ET_PrevAndNext(
			long occurrenceId, String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByOS_ET_PrevAndNext(
			occurrenceId, occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Removes all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63; from the database.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 */
	public static void removeByOS_ET(String occurrenceStatus, long endTime) {
		getPersistence().removeByOS_ET(occurrenceStatus, endTime);
	}

	/**
	 * Returns the number of powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the number of matching powwow meeting occurrences
	 */
	public static int countByOS_ET(String occurrenceStatus, long endTime) {
		return getPersistence().countByOS_ET(occurrenceStatus, endTime);
	}

	/**
	 * Returns all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime) {

		return getPersistence().findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end) {

		return getPersistence().findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, start, end);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end, OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end, OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence findByPMI_OS_ET_First(
			long powwowMeetingId, String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPMI_OS_ET_First(
			powwowMeetingId, occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence fetchByPMI_OS_ET_First(
		long powwowMeetingId, String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().fetchByPMI_OS_ET_First(
			powwowMeetingId, occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence findByPMI_OS_ET_Last(
			long powwowMeetingId, String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPMI_OS_ET_Last(
			powwowMeetingId, occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public static PowwowMeetingOccurrence fetchByPMI_OS_ET_Last(
		long powwowMeetingId, String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().fetchByPMI_OS_ET_Last(
			powwowMeetingId, occurrenceStatus, endTime, orderByComparator);
	}

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static PowwowMeetingOccurrence[] findByPMI_OS_ET_PrevAndNext(
			long occurrenceId, long powwowMeetingId, String occurrenceStatus,
			long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPMI_OS_ET_PrevAndNext(
			occurrenceId, powwowMeetingId, occurrenceStatus, endTime,
			orderByComparator);
	}

	/**
	 * Removes all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63; from the database.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 */
	public static void removeByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime) {

		getPersistence().removeByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime);
	}

	/**
	 * Returns the number of powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the number of matching powwow meeting occurrences
	 */
	public static int countByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime) {

		return getPersistence().countByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime);
	}

	/**
	 * Caches the powwow meeting occurrence in the entity cache if it is enabled.
	 *
	 * @param powwowMeetingOccurrence the powwow meeting occurrence
	 */
	public static void cacheResult(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		getPersistence().cacheResult(powwowMeetingOccurrence);
	}

	/**
	 * Caches the powwow meeting occurrences in the entity cache if it is enabled.
	 *
	 * @param powwowMeetingOccurrences the powwow meeting occurrences
	 */
	public static void cacheResult(
		List<PowwowMeetingOccurrence> powwowMeetingOccurrences) {

		getPersistence().cacheResult(powwowMeetingOccurrences);
	}

	/**
	 * Creates a new powwow meeting occurrence with the primary key. Does not add the powwow meeting occurrence to the database.
	 *
	 * @param occurrenceId the primary key for the new powwow meeting occurrence
	 * @return the new powwow meeting occurrence
	 */
	public static PowwowMeetingOccurrence create(long occurrenceId) {
		return getPersistence().create(occurrenceId);
	}

	/**
	 * Removes the powwow meeting occurrence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was removed
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static PowwowMeetingOccurrence remove(long occurrenceId)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().remove(occurrenceId);
	}

	public static PowwowMeetingOccurrence updateImpl(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		return getPersistence().updateImpl(powwowMeetingOccurrence);
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key or throws a <code>NoSuchMeetingOccurrenceException</code> if it could not be found.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public static PowwowMeetingOccurrence findByPrimaryKey(long occurrenceId)
		throws com.liferay.powwow.exception.NoSuchMeetingOccurrenceException {

		return getPersistence().findByPrimaryKey(occurrenceId);
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence, or <code>null</code> if a powwow meeting occurrence with the primary key could not be found
	 */
	public static PowwowMeetingOccurrence fetchByPrimaryKey(long occurrenceId) {
		return getPersistence().fetchByPrimaryKey(occurrenceId);
	}

	/**
	 * Returns all the powwow meeting occurrences.
	 *
	 * @return the powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findAll(
		int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of powwow meeting occurrences
	 */
	public static List<PowwowMeetingOccurrence> findAll(
		int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the powwow meeting occurrences from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of powwow meeting occurrences.
	 *
	 * @return the number of powwow meeting occurrences
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PowwowMeetingOccurrencePersistence getPersistence() {
		if (_persistence == null) {
			_persistence =
				(PowwowMeetingOccurrencePersistence)
					PortletBeanLocatorUtil.locate(
						com.liferay.powwow.service.ServletContextUtil.
							getServletContextName(),
						PowwowMeetingOccurrencePersistence.class.getName());
		}

		return _persistence;
	}

	private static PowwowMeetingOccurrencePersistence _persistence;

}