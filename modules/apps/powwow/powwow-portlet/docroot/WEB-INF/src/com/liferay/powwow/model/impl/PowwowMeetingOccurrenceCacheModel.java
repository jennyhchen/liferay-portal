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

package com.liferay.powwow.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.powwow.model.PowwowMeetingOccurrence;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PowwowMeetingOccurrence in entity cache.
 *
 * @author Shinn Lok
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrenceCacheModel
	implements CacheModel<PowwowMeetingOccurrence>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PowwowMeetingOccurrenceCacheModel)) {
			return false;
		}

		PowwowMeetingOccurrenceCacheModel powwowMeetingOccurrenceCacheModel =
			(PowwowMeetingOccurrenceCacheModel)obj;

		if (occurrenceId == powwowMeetingOccurrenceCacheModel.occurrenceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, occurrenceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{occurrenceId=");
		sb.append(occurrenceId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append(", powwowMeetingId=");
		sb.append(powwowMeetingId);
		sb.append(", zoomOriginalData=");
		sb.append(zoomOriginalData);
		sb.append(", occurrenceStatus=");
		sb.append(occurrenceStatus);
		sb.append(", occurrenceApiId=");
		sb.append(occurrenceApiId);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PowwowMeetingOccurrence toEntityModel() {
		PowwowMeetingOccurrenceImpl powwowMeetingOccurrenceImpl =
			new PowwowMeetingOccurrenceImpl();

		powwowMeetingOccurrenceImpl.setOccurrenceId(occurrenceId);
		powwowMeetingOccurrenceImpl.setCompanyId(companyId);
		powwowMeetingOccurrenceImpl.setUserId(userId);

		if (userName == null) {
			powwowMeetingOccurrenceImpl.setUserName("");
		}
		else {
			powwowMeetingOccurrenceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			powwowMeetingOccurrenceImpl.setCreateDate(null);
		}
		else {
			powwowMeetingOccurrenceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			powwowMeetingOccurrenceImpl.setModifiedDate(null);
		}
		else {
			powwowMeetingOccurrenceImpl.setModifiedDate(new Date(modifiedDate));
		}

		powwowMeetingOccurrenceImpl.setCalendarBookingId(calendarBookingId);
		powwowMeetingOccurrenceImpl.setPowwowMeetingId(powwowMeetingId);

		if (zoomOriginalData == null) {
			powwowMeetingOccurrenceImpl.setZoomOriginalData("");
		}
		else {
			powwowMeetingOccurrenceImpl.setZoomOriginalData(zoomOriginalData);
		}

		if (occurrenceStatus == null) {
			powwowMeetingOccurrenceImpl.setOccurrenceStatus("");
		}
		else {
			powwowMeetingOccurrenceImpl.setOccurrenceStatus(occurrenceStatus);
		}

		if (occurrenceApiId == null) {
			powwowMeetingOccurrenceImpl.setOccurrenceApiId("");
		}
		else {
			powwowMeetingOccurrenceImpl.setOccurrenceApiId(occurrenceApiId);
		}

		powwowMeetingOccurrenceImpl.setStartTime(startTime);
		powwowMeetingOccurrenceImpl.setEndTime(endTime);

		powwowMeetingOccurrenceImpl.resetOriginalValues();

		return powwowMeetingOccurrenceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		occurrenceId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		calendarBookingId = objectInput.readLong();

		powwowMeetingId = objectInput.readLong();
		zoomOriginalData = objectInput.readUTF();
		occurrenceStatus = objectInput.readUTF();
		occurrenceApiId = objectInput.readUTF();

		startTime = objectInput.readLong();

		endTime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(occurrenceId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(calendarBookingId);

		objectOutput.writeLong(powwowMeetingId);

		if (zoomOriginalData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(zoomOriginalData);
		}

		if (occurrenceStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(occurrenceStatus);
		}

		if (occurrenceApiId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(occurrenceApiId);
		}

		objectOutput.writeLong(startTime);

		objectOutput.writeLong(endTime);
	}

	public long occurrenceId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long calendarBookingId;
	public long powwowMeetingId;
	public String zoomOriginalData;
	public String occurrenceStatus;
	public String occurrenceApiId;
	public long startTime;
	public long endTime;

}