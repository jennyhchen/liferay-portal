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

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.powwow.exception.NoSuchMeetingOccurrenceException;
import com.liferay.powwow.model.PowwowMeetingOccurrence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the powwow meeting occurrence service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceUtil
 * @generated
 */
@ProviderType
public interface PowwowMeetingOccurrencePersistence
	extends BasePersistence<PowwowMeetingOccurrence> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PowwowMeetingOccurrenceUtil} to access the powwow meeting occurrence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, PowwowMeetingOccurrence> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @return the matching powwow meeting occurrences
	 */
	public java.util.List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId);

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
	public java.util.List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end);

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
	public java.util.List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public java.util.List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence findByPowwowMeetingId_First(
			long powwowMeetingId,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence fetchByPowwowMeetingId_First(
		long powwowMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence findByPowwowMeetingId_Last(
			long powwowMeetingId,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence fetchByPowwowMeetingId_Last(
		long powwowMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public PowwowMeetingOccurrence[] findByPowwowMeetingId_PrevAndNext(
			long occurrenceId, long powwowMeetingId,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Removes all the powwow meeting occurrences where powwowMeetingId = &#63; from the database.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 */
	public void removeByPowwowMeetingId(long powwowMeetingId);

	/**
	 * Returns the number of powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @return the number of matching powwow meeting occurrences
	 */
	public int countByPowwowMeetingId(long powwowMeetingId);

	/**
	 * Returns all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the matching powwow meeting occurrences
	 */
	public java.util.List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime);

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
	public java.util.List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end);

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
	public java.util.List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public java.util.List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence findByOS_ET_First(
			String occurrenceStatus, long endTime,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence fetchByOS_ET_First(
		String occurrenceStatus, long endTime,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence findByOS_ET_Last(
			String occurrenceStatus, long endTime,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence fetchByOS_ET_Last(
		String occurrenceStatus, long endTime,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public PowwowMeetingOccurrence[] findByOS_ET_PrevAndNext(
			long occurrenceId, String occurrenceStatus, long endTime,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Removes all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63; from the database.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 */
	public void removeByOS_ET(String occurrenceStatus, long endTime);

	/**
	 * Returns the number of powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the number of matching powwow meeting occurrences
	 */
	public int countByOS_ET(String occurrenceStatus, long endTime);

	/**
	 * Returns all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the matching powwow meeting occurrences
	 */
	public java.util.List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime);

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
	public java.util.List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end);

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
	public java.util.List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public java.util.List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache);

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
	public PowwowMeetingOccurrence findByPMI_OS_ET_First(
			long powwowMeetingId, String occurrenceStatus, long endTime,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence fetchByPMI_OS_ET_First(
		long powwowMeetingId, String occurrenceStatus, long endTime,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public PowwowMeetingOccurrence findByPMI_OS_ET_Last(
			long powwowMeetingId, String occurrenceStatus, long endTime,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	public PowwowMeetingOccurrence fetchByPMI_OS_ET_Last(
		long powwowMeetingId, String occurrenceStatus, long endTime,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public PowwowMeetingOccurrence[] findByPMI_OS_ET_PrevAndNext(
			long occurrenceId, long powwowMeetingId, String occurrenceStatus,
			long endTime,
			com.liferay.portal.kernel.util.OrderByComparator
				<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Removes all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63; from the database.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 */
	public void removeByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime);

	/**
	 * Returns the number of powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the number of matching powwow meeting occurrences
	 */
	public int countByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime);

	/**
	 * Caches the powwow meeting occurrence in the entity cache if it is enabled.
	 *
	 * @param powwowMeetingOccurrence the powwow meeting occurrence
	 */
	public void cacheResult(PowwowMeetingOccurrence powwowMeetingOccurrence);

	/**
	 * Caches the powwow meeting occurrences in the entity cache if it is enabled.
	 *
	 * @param powwowMeetingOccurrences the powwow meeting occurrences
	 */
	public void cacheResult(
		java.util.List<PowwowMeetingOccurrence> powwowMeetingOccurrences);

	/**
	 * Creates a new powwow meeting occurrence with the primary key. Does not add the powwow meeting occurrence to the database.
	 *
	 * @param occurrenceId the primary key for the new powwow meeting occurrence
	 * @return the new powwow meeting occurrence
	 */
	public PowwowMeetingOccurrence create(long occurrenceId);

	/**
	 * Removes the powwow meeting occurrence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was removed
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public PowwowMeetingOccurrence remove(long occurrenceId)
		throws NoSuchMeetingOccurrenceException;

	public PowwowMeetingOccurrence updateImpl(
		PowwowMeetingOccurrence powwowMeetingOccurrence);

	/**
	 * Returns the powwow meeting occurrence with the primary key or throws a <code>NoSuchMeetingOccurrenceException</code> if it could not be found.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	public PowwowMeetingOccurrence findByPrimaryKey(long occurrenceId)
		throws NoSuchMeetingOccurrenceException;

	/**
	 * Returns the powwow meeting occurrence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence, or <code>null</code> if a powwow meeting occurrence with the primary key could not be found
	 */
	public PowwowMeetingOccurrence fetchByPrimaryKey(long occurrenceId);

	/**
	 * Returns all the powwow meeting occurrences.
	 *
	 * @return the powwow meeting occurrences
	 */
	public java.util.List<PowwowMeetingOccurrence> findAll();

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
	public java.util.List<PowwowMeetingOccurrence> findAll(int start, int end);

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
	public java.util.List<PowwowMeetingOccurrence> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator);

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
	public java.util.List<PowwowMeetingOccurrence> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the powwow meeting occurrences from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of powwow meeting occurrences.
	 *
	 * @return the number of powwow meeting occurrences
	 */
	public int countAll();

}