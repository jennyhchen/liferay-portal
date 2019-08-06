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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the PowwowMeetingOccurrence service. Represents a row in the &quot;PowwowMeetingOccurrence&quot; database table, with each column mapped to a property of this class.
 *
 * @author Shinn Lok
 * @see PowwowMeetingOccurrenceModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.powwow.model.impl.PowwowMeetingOccurrenceImpl"
)
@ProviderType
public interface PowwowMeetingOccurrence
	extends PersistedModel, PowwowMeetingOccurrenceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.powwow.model.impl.PowwowMeetingOccurrenceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PowwowMeetingOccurrence, Long>
		OCCURRENCE_ID_ACCESSOR = new Accessor<PowwowMeetingOccurrence, Long>() {

			@Override
			public Long get(PowwowMeetingOccurrence powwowMeetingOccurrence) {
				return powwowMeetingOccurrence.getOccurrenceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PowwowMeetingOccurrence> getTypeClass() {
				return PowwowMeetingOccurrence.class;
			}

		};

	public com.liferay.powwow.occurrence.OccurrenceStatus
		getOccurrenceStatusEnum();

	public boolean isEndTimePassed();

}