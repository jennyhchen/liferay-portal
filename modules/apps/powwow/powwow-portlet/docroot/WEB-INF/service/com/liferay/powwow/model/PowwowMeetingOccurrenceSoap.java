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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.powwow.service.http.PowwowMeetingOccurrenceServiceSoap}.
 *
 * @author Shinn Lok
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceSoap implements Serializable {

	public static PowwowMeetingOccurrenceSoap toSoapModel(
		PowwowMeetingOccurrence model) {

		PowwowMeetingOccurrenceSoap soapModel =
			new PowwowMeetingOccurrenceSoap();

		soapModel.setOccurrenceId(model.getOccurrenceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());
		soapModel.setPowwowMeetingId(model.getPowwowMeetingId());
		soapModel.setZoomOriginalData(model.getZoomOriginalData());
		soapModel.setOccurrenceStatus(model.getOccurrenceStatus());
		soapModel.setOccurrenceApiId(model.getOccurrenceApiId());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());

		return soapModel;
	}

	public static PowwowMeetingOccurrenceSoap[] toSoapModels(
		PowwowMeetingOccurrence[] models) {

		PowwowMeetingOccurrenceSoap[] soapModels =
			new PowwowMeetingOccurrenceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PowwowMeetingOccurrenceSoap[][] toSoapModels(
		PowwowMeetingOccurrence[][] models) {

		PowwowMeetingOccurrenceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new PowwowMeetingOccurrenceSoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new PowwowMeetingOccurrenceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PowwowMeetingOccurrenceSoap[] toSoapModels(
		List<PowwowMeetingOccurrence> models) {

		List<PowwowMeetingOccurrenceSoap> soapModels =
			new ArrayList<PowwowMeetingOccurrenceSoap>(models.size());

		for (PowwowMeetingOccurrence model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new PowwowMeetingOccurrenceSoap[soapModels.size()]);
	}

	public PowwowMeetingOccurrenceSoap() {
	}

	public long getPrimaryKey() {
		return _occurrenceId;
	}

	public void setPrimaryKey(long pk) {
		setOccurrenceId(pk);
	}

	public long getOccurrenceId() {
		return _occurrenceId;
	}

	public void setOccurrenceId(long occurrenceId) {
		_occurrenceId = occurrenceId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	public long getPowwowMeetingId() {
		return _powwowMeetingId;
	}

	public void setPowwowMeetingId(long powwowMeetingId) {
		_powwowMeetingId = powwowMeetingId;
	}

	public String getZoomOriginalData() {
		return _zoomOriginalData;
	}

	public void setZoomOriginalData(String zoomOriginalData) {
		_zoomOriginalData = zoomOriginalData;
	}

	public String getOccurrenceStatus() {
		return _occurrenceStatus;
	}

	public void setOccurrenceStatus(String occurrenceStatus) {
		_occurrenceStatus = occurrenceStatus;
	}

	public String getOccurrenceApiId() {
		return _occurrenceApiId;
	}

	public void setOccurrenceApiId(String occurrenceApiId) {
		_occurrenceApiId = occurrenceApiId;
	}

	public long getStartTime() {
		return _startTime;
	}

	public void setStartTime(long startTime) {
		_startTime = startTime;
	}

	public long getEndTime() {
		return _endTime;
	}

	public void setEndTime(long endTime) {
		_endTime = endTime;
	}

	private long _occurrenceId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _calendarBookingId;
	private long _powwowMeetingId;
	private String _zoomOriginalData;
	private String _occurrenceStatus;
	private String _occurrenceApiId;
	private long _startTime;
	private long _endTime;

}