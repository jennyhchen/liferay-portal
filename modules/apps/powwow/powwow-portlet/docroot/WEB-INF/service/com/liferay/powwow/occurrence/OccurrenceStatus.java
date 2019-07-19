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

package com.liferay.powwow.occurrence;

/**
 * @author Vu Ho
 */
public enum OccurrenceStatus {

	AVAILABLE("available", "scheduled"), COMPLETED("completed", "completed"),
	DELETE("deleted", "deleted");

	public static OccurrenceStatus parse(String value) {
		String status = AVAILABLE.getValue();

		if (status.equals(value)) {
			return AVAILABLE;
		}

		status = COMPLETED.getValue();

		if (status.equals(value)) {
			return COMPLETED;
		}

		status = DELETE.getValue();

		if (status.equals(value)) {
			return DELETE;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public final String getLanguageKey() {
		return _languageKey;
	}

	public final String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private OccurrenceStatus(String value, String languageKey) {
		_value = value;
		_languageKey = languageKey;
	}

	private final String _languageKey;
	private final String _value;

}