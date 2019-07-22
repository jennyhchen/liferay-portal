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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link PowwowMeetingOccurrence}.
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrence
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceWrapper
	implements PowwowMeetingOccurrence, ModelWrapper<PowwowMeetingOccurrence> {

	public PowwowMeetingOccurrenceWrapper(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		_powwowMeetingOccurrence = powwowMeetingOccurrence;
	}

	@Override
	public Class<?> getModelClass() {
		return PowwowMeetingOccurrence.class;
	}

	@Override
	public String getModelClassName() {
		return PowwowMeetingOccurrence.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("occurrenceId", getOccurrenceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("calendarBookingId", getCalendarBookingId());
		attributes.put("powwowMeetingId", getPowwowMeetingId());
		attributes.put("zoomOriginalData", getZoomOriginalData());
		attributes.put("occurrenceStatus", getOccurrenceStatus());
		attributes.put("occurrenceApiId", getOccurrenceApiId());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long occurrenceId = (Long)attributes.get("occurrenceId");

		if (occurrenceId != null) {
			setOccurrenceId(occurrenceId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
		}

		Long powwowMeetingId = (Long)attributes.get("powwowMeetingId");

		if (powwowMeetingId != null) {
			setPowwowMeetingId(powwowMeetingId);
		}

		String zoomOriginalData = (String)attributes.get("zoomOriginalData");

		if (zoomOriginalData != null) {
			setZoomOriginalData(zoomOriginalData);
		}

		String occurrenceStatus = (String)attributes.get("occurrenceStatus");

		if (occurrenceStatus != null) {
			setOccurrenceStatus(occurrenceStatus);
		}

		String occurrenceApiId = (String)attributes.get("occurrenceApiId");

		if (occurrenceApiId != null) {
			setOccurrenceApiId(occurrenceApiId);
		}

		Long startTime = (Long)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Long endTime = (Long)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}
	}

	@Override
	public Object clone() {
		return new PowwowMeetingOccurrenceWrapper(
			(PowwowMeetingOccurrence)_powwowMeetingOccurrence.clone());
	}

	@Override
	public int compareTo(PowwowMeetingOccurrence powwowMeetingOccurrence) {
		return _powwowMeetingOccurrence.compareTo(powwowMeetingOccurrence);
	}

	/**
	 * Returns the calendar booking ID of this powwow meeting occurrence.
	 *
	 * @return the calendar booking ID of this powwow meeting occurrence
	 */
	@Override
	public long getCalendarBookingId() {
		return _powwowMeetingOccurrence.getCalendarBookingId();
	}

	/**
	 * Returns the company ID of this powwow meeting occurrence.
	 *
	 * @return the company ID of this powwow meeting occurrence
	 */
	@Override
	public long getCompanyId() {
		return _powwowMeetingOccurrence.getCompanyId();
	}

	/**
	 * Returns the create date of this powwow meeting occurrence.
	 *
	 * @return the create date of this powwow meeting occurrence
	 */
	@Override
	public Date getCreateDate() {
		return _powwowMeetingOccurrence.getCreateDate();
	}

	/**
	 * Returns the end time of this powwow meeting occurrence.
	 *
	 * @return the end time of this powwow meeting occurrence
	 */
	@Override
	public long getEndTime() {
		return _powwowMeetingOccurrence.getEndTime();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _powwowMeetingOccurrence.getExpandoBridge();
	}

	/**
	 * Returns the modified date of this powwow meeting occurrence.
	 *
	 * @return the modified date of this powwow meeting occurrence
	 */
	@Override
	public Date getModifiedDate() {
		return _powwowMeetingOccurrence.getModifiedDate();
	}

	/**
	 * Returns the occurrence api ID of this powwow meeting occurrence.
	 *
	 * @return the occurrence api ID of this powwow meeting occurrence
	 */
	@Override
	public String getOccurrenceApiId() {
		return _powwowMeetingOccurrence.getOccurrenceApiId();
	}

	/**
	 * Returns the occurrence ID of this powwow meeting occurrence.
	 *
	 * @return the occurrence ID of this powwow meeting occurrence
	 */
	@Override
	public long getOccurrenceId() {
		return _powwowMeetingOccurrence.getOccurrenceId();
	}

	/**
	 * Returns the occurrence status of this powwow meeting occurrence.
	 *
	 * @return the occurrence status of this powwow meeting occurrence
	 */
	@Override
	public String getOccurrenceStatus() {
		return _powwowMeetingOccurrence.getOccurrenceStatus();
	}

	@Override
	public com.liferay.powwow.occurrence.OccurrenceStatus
		getOccurrenceStatusEnum() {

		return _powwowMeetingOccurrence.getOccurrenceStatusEnum();
	}

	/**
	 * Returns the powwow meeting ID of this powwow meeting occurrence.
	 *
	 * @return the powwow meeting ID of this powwow meeting occurrence
	 */
	@Override
	public long getPowwowMeetingId() {
		return _powwowMeetingOccurrence.getPowwowMeetingId();
	}

	/**
	 * Returns the primary key of this powwow meeting occurrence.
	 *
	 * @return the primary key of this powwow meeting occurrence
	 */
	@Override
	public long getPrimaryKey() {
		return _powwowMeetingOccurrence.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _powwowMeetingOccurrence.getPrimaryKeyObj();
	}

	/**
	 * Returns the start time of this powwow meeting occurrence.
	 *
	 * @return the start time of this powwow meeting occurrence
	 */
	@Override
	public long getStartTime() {
		return _powwowMeetingOccurrence.getStartTime();
	}

	/**
	 * Returns the user ID of this powwow meeting occurrence.
	 *
	 * @return the user ID of this powwow meeting occurrence
	 */
	@Override
	public long getUserId() {
		return _powwowMeetingOccurrence.getUserId();
	}

	/**
	 * Returns the user name of this powwow meeting occurrence.
	 *
	 * @return the user name of this powwow meeting occurrence
	 */
	@Override
	public String getUserName() {
		return _powwowMeetingOccurrence.getUserName();
	}

	/**
	 * Returns the user uuid of this powwow meeting occurrence.
	 *
	 * @return the user uuid of this powwow meeting occurrence
	 */
	@Override
	public String getUserUuid() {
		return _powwowMeetingOccurrence.getUserUuid();
	}

	/**
	 * Returns the zoom original data of this powwow meeting occurrence.
	 *
	 * @return the zoom original data of this powwow meeting occurrence
	 */
	@Override
	public String getZoomOriginalData() {
		return _powwowMeetingOccurrence.getZoomOriginalData();
	}

	@Override
	public int hashCode() {
		return _powwowMeetingOccurrence.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _powwowMeetingOccurrence.isCachedModel();
	}

	@Override
	public boolean isEndTimePassed() {
		return _powwowMeetingOccurrence.isEndTimePassed();
	}

	@Override
	public boolean isEscapedModel() {
		return _powwowMeetingOccurrence.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _powwowMeetingOccurrence.isNew();
	}

	@Override
	public void persist() {
		_powwowMeetingOccurrence.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_powwowMeetingOccurrence.setCachedModel(cachedModel);
	}

	/**
	 * Sets the calendar booking ID of this powwow meeting occurrence.
	 *
	 * @param calendarBookingId the calendar booking ID of this powwow meeting occurrence
	 */
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_powwowMeetingOccurrence.setCalendarBookingId(calendarBookingId);
	}

	/**
	 * Sets the company ID of this powwow meeting occurrence.
	 *
	 * @param companyId the company ID of this powwow meeting occurrence
	 */
	@Override
	public void setCompanyId(long companyId) {
		_powwowMeetingOccurrence.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this powwow meeting occurrence.
	 *
	 * @param createDate the create date of this powwow meeting occurrence
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_powwowMeetingOccurrence.setCreateDate(createDate);
	}

	/**
	 * Sets the end time of this powwow meeting occurrence.
	 *
	 * @param endTime the end time of this powwow meeting occurrence
	 */
	@Override
	public void setEndTime(long endTime) {
		_powwowMeetingOccurrence.setEndTime(endTime);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_powwowMeetingOccurrence.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_powwowMeetingOccurrence.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_powwowMeetingOccurrence.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the modified date of this powwow meeting occurrence.
	 *
	 * @param modifiedDate the modified date of this powwow meeting occurrence
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_powwowMeetingOccurrence.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_powwowMeetingOccurrence.setNew(n);
	}

	/**
	 * Sets the occurrence api ID of this powwow meeting occurrence.
	 *
	 * @param occurrenceApiId the occurrence api ID of this powwow meeting occurrence
	 */
	@Override
	public void setOccurrenceApiId(String occurrenceApiId) {
		_powwowMeetingOccurrence.setOccurrenceApiId(occurrenceApiId);
	}

	/**
	 * Sets the occurrence ID of this powwow meeting occurrence.
	 *
	 * @param occurrenceId the occurrence ID of this powwow meeting occurrence
	 */
	@Override
	public void setOccurrenceId(long occurrenceId) {
		_powwowMeetingOccurrence.setOccurrenceId(occurrenceId);
	}

	/**
	 * Sets the occurrence status of this powwow meeting occurrence.
	 *
	 * @param occurrenceStatus the occurrence status of this powwow meeting occurrence
	 */
	@Override
	public void setOccurrenceStatus(String occurrenceStatus) {
		_powwowMeetingOccurrence.setOccurrenceStatus(occurrenceStatus);
	}

	/**
	 * Sets the powwow meeting ID of this powwow meeting occurrence.
	 *
	 * @param powwowMeetingId the powwow meeting ID of this powwow meeting occurrence
	 */
	@Override
	public void setPowwowMeetingId(long powwowMeetingId) {
		_powwowMeetingOccurrence.setPowwowMeetingId(powwowMeetingId);
	}

	/**
	 * Sets the primary key of this powwow meeting occurrence.
	 *
	 * @param primaryKey the primary key of this powwow meeting occurrence
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_powwowMeetingOccurrence.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_powwowMeetingOccurrence.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the start time of this powwow meeting occurrence.
	 *
	 * @param startTime the start time of this powwow meeting occurrence
	 */
	@Override
	public void setStartTime(long startTime) {
		_powwowMeetingOccurrence.setStartTime(startTime);
	}

	/**
	 * Sets the user ID of this powwow meeting occurrence.
	 *
	 * @param userId the user ID of this powwow meeting occurrence
	 */
	@Override
	public void setUserId(long userId) {
		_powwowMeetingOccurrence.setUserId(userId);
	}

	/**
	 * Sets the user name of this powwow meeting occurrence.
	 *
	 * @param userName the user name of this powwow meeting occurrence
	 */
	@Override
	public void setUserName(String userName) {
		_powwowMeetingOccurrence.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this powwow meeting occurrence.
	 *
	 * @param userUuid the user uuid of this powwow meeting occurrence
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_powwowMeetingOccurrence.setUserUuid(userUuid);
	}

	/**
	 * Sets the zoom original data of this powwow meeting occurrence.
	 *
	 * @param zoomOriginalData the zoom original data of this powwow meeting occurrence
	 */
	@Override
	public void setZoomOriginalData(String zoomOriginalData) {
		_powwowMeetingOccurrence.setZoomOriginalData(zoomOriginalData);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PowwowMeetingOccurrence>
		toCacheModel() {

		return _powwowMeetingOccurrence.toCacheModel();
	}

	@Override
	public PowwowMeetingOccurrence toEscapedModel() {
		return new PowwowMeetingOccurrenceWrapper(
			_powwowMeetingOccurrence.toEscapedModel());
	}

	@Override
	public String toString() {
		return _powwowMeetingOccurrence.toString();
	}

	@Override
	public PowwowMeetingOccurrence toUnescapedModel() {
		return new PowwowMeetingOccurrenceWrapper(
			_powwowMeetingOccurrence.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _powwowMeetingOccurrence.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PowwowMeetingOccurrenceWrapper)) {
			return false;
		}

		PowwowMeetingOccurrenceWrapper powwowMeetingOccurrenceWrapper =
			(PowwowMeetingOccurrenceWrapper)obj;

		if (Objects.equals(
				_powwowMeetingOccurrence,
				powwowMeetingOccurrenceWrapper._powwowMeetingOccurrence)) {

			return true;
		}

		return false;
	}

	@Override
	public PowwowMeetingOccurrence getWrappedModel() {
		return _powwowMeetingOccurrence;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _powwowMeetingOccurrence.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _powwowMeetingOccurrence.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_powwowMeetingOccurrence.resetOriginalValues();
	}

	private final PowwowMeetingOccurrence _powwowMeetingOccurrence;

}